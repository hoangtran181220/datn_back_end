package do_an.controller;

import do_an.common.CommonUtils;
import do_an.common.ErrorCode;
import do_an.common.ObjectError;
import do_an.common.ResultResp;
import do_an.dto.FileExcelDTO;
import do_an.exception.CustomExceptionHandler;
import do_an.service.FileExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileExcelController {
    @Autowired
    private FileExcelService fileExcelService;

    @Autowired
    private ServletContext servletContext;

    @PostMapping("/upload")
    public ResultResp uploadFile(@RequestParam("files") List<MultipartFile> files, @RequestParam Integer type) {
        try {
            List<FileExcelDTO> response = fileExcelService.upload(files, type);
            if (response == null) {
                return ResultResp.badRequest(ErrorCode.SELECT_FAIL);
            }
            return ResultResp.success(ErrorCode.UPDATED_OK, response);
        } catch (CustomExceptionHandler e) {
            return ResultResp.badRequest(new ObjectError(e.getMsgCode(), e.getMessage()));
        } catch (Exception e) {
            return ResultResp.badRequest(new ObjectError(ErrorCode.SELECT_FAIL.getCode(), e.getMessage()));
        }
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> dowloadFile(@PathVariable(required = false) Long id) {
        try {
            FileExcelDTO fileAttachDTO = fileExcelService.findById(id);
            if (fileAttachDTO == null) {
                return ResultResp.badRequest(new ObjectError("EAD001", "Không tìm thấy file trên hệ thống!"));
            }

            MediaType mediaType = CommonUtils.getMediaTypeForFileName(this.servletContext, fileAttachDTO.getFileName());
            System.out.println("fileName: " + fileAttachDTO.getFileName());
            System.out.println("mediaType: " + mediaType);
            File file = new File(fileAttachDTO.getFilePath());

            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileAttachDTO.getFileName());
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(mediaType)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResp.badRequest(new ObjectError("EAD001", "Không tìm thấy file trên hệ thống!"));
        }
    }
}
