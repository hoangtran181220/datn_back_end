package do_an.service;

import do_an.dto.DetailRequestDTO;
import do_an.searchform.DetailRequestSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DetailRequestService {
    String createOrUpdate(do_an.dto.DetailRequestDTO detailRequestDTO);

    String delete(Long id);

    do_an.dto.DetailRequestDTO findById(Long id);

    Page<DetailRequestDTO> search(DetailRequestSearchForm searchForm, Pageable pageable);
}
