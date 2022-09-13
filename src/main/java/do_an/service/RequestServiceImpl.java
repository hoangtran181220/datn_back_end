package do_an.service;

import do_an.common.Constants;
import do_an.dto.RequestDTO;
import do_an.entity.RequestEntity;
import do_an.repository.RequestRepository;
import do_an.searchform.RequestSearchForm;
import do_an.service.mapper.RequestMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RequestServiceImpl implements RequestService {
    private final Logger log = LogManager.getLogger(RequestServiceImpl.class);
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FileExcelService fileExcelService;

    @Override
    public String createOrUpdate(do_an.dto.RequestDTO requestDTO) {

        if (requestDTO.getId() == null) {

            do_an.entity.RequestEntity checkCode = requestRepository.findByCodeAndIsActive(requestDTO.getCode(), do_an.common.Constants.STATE.ACTIVE);
            if (checkCode != null) {
                log.error("error: Code is exist!");
                return "-2";
            }
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(date).replace("/", "");
            do_an.entity.RequestEntity requestEntity = requestMapper.toEntity(requestDTO);
            requestEntity.setCode(strDate+"-"+requestDTO.getCode());
            requestEntity.setIsActive(do_an.common.Constants.STATE.ACTIVE);
            requestEntity.setStatus(0);
            requestEntity.setTimeSendYc(new Date());
            requestRepository.save(requestEntity);
        } else {
            do_an.entity.RequestEntity requestEntity = requestRepository.findById(requestDTO.getId()).orElse(null);
            if (requestEntity == null) {
                log.error("not found entity");
                return "-1";
            }
            requestEntity.setCode(requestDTO.getCode());
            requestEntity.setTitle(requestDTO.getTitle());
            requestEntity.setIdNv(requestDTO.getIdNv());
            requestEntity.setEmailNvYc(requestDTO.getEmailNvYc());
            requestEntity.setIdExcel(requestDTO.getIdExcel());
            requestEntity.setNameNvYc(requestDTO.getNameNvYc());

            requestRepository.save(requestEntity);
        }

        return "1";
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            log.error("--- Error id is null---");
            return "-1";
        }
        do_an.entity.RequestEntity requestEntity = requestRepository.findById(id).orElse(null);
        if (requestEntity == null) {
                log.error("error: not found requestDTO");
            return "-1";
        }
        requestEntity.setIsActive(Constants.STATE.DELETED);
        requestRepository.save(requestEntity);
        return "1";
    }

    @Override
    public do_an.dto.RequestDTO findById(Long id) {
        if (id == null) {
            log.error("--- Error id is null ---");
            return null;
        }
        do_an.entity.RequestEntity requestEntity = requestRepository.findById(id).orElse(null);
        if (requestEntity == null) {
            log.error("error: not found requestDTO");
            return null;
        }
        do_an.dto.RequestDTO requestDTO = requestMapper.toDto(requestEntity);

        if (requestDTO.getIdExcel() != null) {
            requestDTO.setFileExcelDTO(fileExcelService.findById(requestDTO.getIdExcel().longValue()));
        }

        return requestDTO;
    }

    @Override
    public Page<RequestDTO> search(RequestSearchForm requestSearchForm, Pageable pageable) {

        Page<RequestEntity> pages = requestRepository.searchRq(requestSearchForm.getCode(), requestSearchForm.getTitle() , requestSearchForm.getNameNvYc(), pageable);
        return pages.map(requestMapper::toDto);
    }
}
