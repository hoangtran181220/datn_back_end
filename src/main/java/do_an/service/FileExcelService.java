package do_an.service;

import do_an.dto.FileExcelDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileExcelService {
    List<FileExcelDTO> upload(List<MultipartFile> files, Integer type) throws Exception;

    FileExcelDTO findById(Long id);
}
