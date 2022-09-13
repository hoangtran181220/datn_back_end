package do_an.controller;

import do_an.common.ObjectError;
import do_an.common.ResultResp;
import do_an.dto.DetailRequestDTO;
import do_an.searchform.DetailRequestSearchForm;
import do_an.service.DetailRequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detailRequest")
public class DetailRequestController {
    private final Logger log = LogManager.getLogger(RequestController.class);

    @Autowired
    private DetailRequestService detailRequestService;

    @PostMapping("/search")
    public ResultResp search(@RequestBody(required = false) DetailRequestSearchForm detailRequestSearchForm, Pageable pageable) {
        Page<DetailRequestDTO> pages = detailRequestService.search(detailRequestSearchForm, pageable);
        return ResultResp.success(pages);
    }

    @PostMapping("/create")
    public ResultResp create(@RequestBody DetailRequestDTO detailRequestDTO) {
        String in = detailRequestService.createOrUpdate(detailRequestDTO);

        if (in.equals(-1)) {
            return ResultResp.badRequest(new ObjectError("ERROR", "save error"));
        }
        return ResultResp.success(in);
    }

    @PutMapping("/update/{id}")
    public ResultResp update(@RequestBody DetailRequestDTO detailRequestDTO, @PathVariable Long id) {
        detailRequestDTO.setId(id);

        String in = detailRequestService.createOrUpdate(detailRequestDTO);

        if (in.equals(-1)) {
            return ResultResp.badRequest(new ObjectError("ERROR", "save error"));
        }
        return ResultResp.success(in);
    }

    @GetMapping("/findById/{id}")
    public ResultResp findById(@PathVariable Long id) {

        DetailRequestDTO detailRequestDTO = detailRequestService.findById(id);
        if (detailRequestDTO == null) {
            return ResultResp.badRequest(new ObjectError("ERROR: ", "NOT FOUND InventoryPlaybookDTO"));
        }

        return ResultResp.success(detailRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResultResp delete(@PathVariable Long id) {

        String in = detailRequestService.delete(id);

        if (in.equals(-1)) {
            return ResultResp.badRequest(new ObjectError("ERROR", "delete error"));
        }
        return ResultResp.success(in);
    }
}
