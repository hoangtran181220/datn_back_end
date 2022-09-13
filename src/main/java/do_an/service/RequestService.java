package do_an.service;

import do_an.dto.RequestDTO;
import do_an.searchform.RequestSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RequestService {
    String createOrUpdate(do_an.dto.RequestDTO requestDTO);

    String delete(Long id);

    do_an.dto.RequestDTO findById(Long id);

    Page<RequestDTO> search(RequestSearchForm requestSearchForm, Pageable pageable);
}
