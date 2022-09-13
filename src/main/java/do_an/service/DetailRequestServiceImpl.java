package do_an.service;

import do_an.common.CommonUtils;
import do_an.common.Constants;
import do_an.dto.DetailRequestDTO;
import do_an.entity.DetailRequestEntity;
import do_an.repository.DetailRequestRepository;
import do_an.searchform.DetailRequestSearchForm;
import do_an.service.DetailRequestService;
import do_an.service.mapper.DetailRequestMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DetailRequestServiceImpl implements DetailRequestService{
    private final Logger log = LogManager.getLogger(do_an.service.DetailRequestServiceImpl.class);

    @Autowired
    private DetailRequestRepository detailRequestRepository;

    @Autowired
    private DetailRequestMapper detailRequestMapper;

    @Override
    public String createOrUpdate(DetailRequestDTO detailRequestDTO) {

        if (detailRequestDTO.getId() == null) {
            DetailRequestEntity detailRequestEntity = detailRequestMapper.toEntity(detailRequestDTO);
            detailRequestEntity.setStatus(0);
            detailRequestEntity.setIsActive(Constants.STATE.ACTIVE);

            detailRequestRepository.save(detailRequestEntity);
        } else {
            DetailRequestEntity detailRequestEntity = detailRequestRepository.findById(detailRequestDTO.getId()).orElse(null);
            if (detailRequestEntity == null) {
                log.error("not found entity");
                return "-1";
            }
            detailRequestEntity.setIdNv(detailRequestDTO.getIdNv());
            detailRequestEntity.setTenKhoanTien(detailRequestDTO.getTenKhoanTien());
            detailRequestEntity.setSoTien(detailRequestDTO.getSoTien());
            detailRequestEntity.setThoiGian(detailRequestDTO.getThoiGian());
            detailRequestEntity.setType(detailRequestDTO.getType());

            detailRequestRepository.save(detailRequestEntity);
        }
        return "1";
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            log.error("--- Error id is null---");
            return "-1";
        }
        DetailRequestEntity detailRequestEntity = detailRequestRepository.findById(id).orElse(null);
        if (detailRequestEntity == null) {
            log.error("error: not found Dto");
            return "-1";
        }
        detailRequestEntity.setIsActive(Constants.STATE.DELETED);
        detailRequestRepository.save(detailRequestEntity);
        return "1";
    }

    @Override
    public DetailRequestDTO findById(Long id) {
        if (id == null) {
            log.error("--- Error id is null ---");
            return null;
        }
        DetailRequestEntity detailRequestEntity = detailRequestRepository.findById(id).orElse(null);
        if (detailRequestEntity == null) {
            log.error("error: not found DetailRequestDTO");
            return null;
        }
        DetailRequestDTO detailRequestDTO = detailRequestMapper.toDto(detailRequestEntity);

        return detailRequestDTO;
    }

    @Override
    public Page<DetailRequestDTO> search(DetailRequestSearchForm searchForm, Pageable pageable) {

        Page<DetailRequestEntity> pages = detailRequestRepository.searchDRq(searchForm.getTenKhoanTien(),pageable);
        return pages.map(detailRequestMapper::toDto);
    }
}
