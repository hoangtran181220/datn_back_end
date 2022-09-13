package do_an.service;

import do_an.common.CommonUtils;
import do_an.common.Constants;
import do_an.dto.FileExcelDTO;
import do_an.entity.FileExcelEntity;
import do_an.repository.FileExcelRepository;
import do_an.service.FileExcelService;
import do_an.service.mapper.FileExcelMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileExcelServiceImpl implements FileExcelService{
    private Logger log = LogManager.getLogger(do_an.service.FileExcelServiceImpl.class);

    @Autowired
    private FileExcelRepository fileExcelRepository;

    @Autowired
    private FileExcelMapper fileExcelMapper;

    private final String UPLOAD_FOLDER_ROOT = "upload/";

    @Override
    public List<FileExcelDTO> upload(List<MultipartFile> files, Integer type) throws Exception {
        log.info("--- start upload file ---");
        try {
            List<FileExcelDTO> lstDTO = new ArrayList<>();
            String uploadFolder = UPLOAD_FOLDER_ROOT + CommonUtils.convertDateToString(new Date()) + type;
            for (MultipartFile file : files) {
                String originName = file.getOriginalFilename();
                int i = originName.lastIndexOf('.');
                String uploadName = "";
                String extend = "";
                if (i < 0) {
                    uploadName = CommonUtils.getFileName(originName, null);
                } else {
                    String preName = originName.substring(0, i);
                    extend = originName.substring(i + 1);
                    uploadName = CommonUtils.getFileName(preName, extend);
                }

                File dest = new File(uploadFolder + "/" + uploadName);
                FileUtils.copyInputStreamToFile(file.getInputStream(), dest);

                FileExcelEntity fileExcelEntity = new FileExcelEntity();
                fileExcelEntity.setFileName(originName);
                fileExcelEntity.setFilePath(uploadFolder + "/" + uploadName);
                fileExcelEntity.setFileType(extend);
                fileExcelEntity.setIsActive(Constants.STATE.ACTIVE);
                fileExcelRepository.save(fileExcelEntity);
                lstDTO.add(fileExcelMapper.toDto(fileExcelEntity));
            }
            log.info("--- end upload file ---");
            return lstDTO;
        } catch (Exception e) {
            log.error("--- Error: ---" + e.toString(), e);
            throw new Exception(e);
        }
    }

    @Override
    public FileExcelDTO findById(Long id) {
        log.info("--- start findById fileAttach ---");
        if (id == null) {
            log.error("--- Error id FileAttach is null");
            return null;
        }

        FileExcelEntity file = fileExcelRepository.findById(id).orElse(null);
        if (file == null) {
            log.error("--- Error fileAttach is null ---");
            return null;
        }
        return fileExcelMapper.toDto(file);
    }
}
