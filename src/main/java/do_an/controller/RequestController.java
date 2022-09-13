package do_an.controller;

import do_an.common.ObjectError;
import do_an.common.ResultResp;
import do_an.dto.RequestDTO;
import do_an.searchform.RequestSearchForm;
import do_an.service.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
public class RequestController {
    private final Logger log = LogManager.getLogger(RequestController.class);

    @Autowired
    private RequestService requestService;

    @PostMapping("/search")
    public do_an.common.ResultResp search(@RequestBody(required = false) RequestSearchForm requestSearchForm, Pageable pageable) {
        Page<do_an.dto.RequestDTO> pages = requestService.search(requestSearchForm, pageable);
        return do_an.common.ResultResp.success(pages);
    }

    @PostMapping("/create")
    public do_an.common.ResultResp create(@RequestBody do_an.dto.RequestDTO requestDTO) {
        String in = requestService.createOrUpdate(requestDTO);

        if (in.equals(-1)) {
            return do_an.common.ResultResp.badRequest(new do_an.common.ObjectError("ERROR", "save error"));
        }
        return do_an.common.ResultResp.success(in);
    }

    @PutMapping("/update/{id}")
    public do_an.common.ResultResp update(@RequestBody do_an.dto.RequestDTO requestDTO, @PathVariable Long id) {
        requestDTO.setId(id);

        String in = requestService.createOrUpdate(requestDTO);

        if (in.equals(-1)) {
            return do_an.common.ResultResp.badRequest(new do_an.common.ObjectError("ERROR", "save error"));
        }
        return do_an.common.ResultResp.success(in);
    }

    @GetMapping("/findById/{id}")
    public do_an.common.ResultResp findById(@PathVariable Long id) {

        RequestDTO requestDTO = requestService.findById(id);
        if (requestDTO == null) {
            return do_an.common.ResultResp.badRequest(new do_an.common.ObjectError("ERROR: ", "NOT FOUND InventoryPlaybookDTO"));
        }

        return do_an.common.ResultResp.success(requestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public do_an.common.ResultResp delete(@PathVariable Long id) {

        String in = requestService.delete(id);

        if (in.equals(-1)) {
            return do_an.common.ResultResp.badRequest(new ObjectError("ERROR", "delete error"));
        }
        return ResultResp.success(in);
    }
}
