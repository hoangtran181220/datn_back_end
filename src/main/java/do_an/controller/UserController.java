package do_an.controller;

import do_an.common.ErrorCode;
import do_an.common.ObjectError;
import do_an.common.ResultResp;
import do_an.dto.UserDTO;
import do_an.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LogManager.getLogger(do_an.controller.UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo/{id}")
    public ResultResp getUserInfo(@PathVariable Long id) {
        try {
            UserDTO userDTO = userService.getUserInfo(id);
            return ResultResp.success(userDTO);
        } catch (Exception ex) {
            log.error("get user info error", ex.getMessage());
            return ResultResp.badRequest(ErrorCode.NOT_FOUND);
        }
    }


    @PatchMapping("/changePassword")
    public ResultResp changePassword(@RequestBody UserDTO user) {
        log.info("----- start change password ----- ");
        try {
            Integer result = userService.changePassword(user);
            return ResultResp.success(result);
        } catch (Exception ex) {
            return ResultResp.badRequest(new ObjectError("ERROR", "change pass error"));
        }
    }

    @PostMapping("/create")
    public ResultResp createUser(@RequestBody UserDTO userDto) {
        log.info("----- start createUser ----- : " + userDto.toString());
        try{
            Integer result = userService.createUser(userDto);
            return ResultResp.success(result);
        }catch (Exception ex){
            return ResultResp.badRequest(new ObjectError("ERROR", "create error"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResultResp deleteUser(@PathVariable Long id){
        log.info("----- start delete ----- : " + id);
        try{
            Integer result = userService.deleteUser(id);
            return ResultResp.success(result);
        }catch (Exception ex){
            return ResultResp.badRequest(ErrorCode.NOT_FOUND);
        }
    }
}
