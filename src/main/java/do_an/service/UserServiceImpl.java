package do_an.service;

import do_an.common.Constants;
import do_an.common.ErrorCode;
import do_an.dto.UserDTO;
import do_an.entity.UserEntity;
import do_an.exception.CustomExceptionHandler;
import do_an.repository.UsersRepository;
import do_an.service.UserService;
import do_an.service.mapper.UsersMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    private final Logger log = LogManager.getLogger(do_an.service.UserServiceImpl.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDTO getUserInfo(Long id) {
        UserEntity userEntity = usersRepository.getOne(id);
        UserDTO userDTO = usersMapper.toDto(userEntity);
        return userDTO;
    }

    @Override
    public Integer createUser(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            UserEntity user = usersMapper.toEntity(userDTO);
            user.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
            user.setIsActive(Constants.STATE.ACTIVE);
            usersRepository.save(user);
        }else {
            return 0;
        }
        return 1;
    }

    @Override
    public Integer changePassword(UserDTO userDTO) {

        UserEntity user = usersRepository.findByUserName(userDTO.getUserName());
        if (user == null) {
            throw new CustomExceptionHandler(ErrorCode.USERNAME_NOT_FOUND.getCode(), HttpStatus.BAD_REQUEST);
        }
        user.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
        usersRepository.save(user);

        return 1;
    }

    @Override
    public Integer deleteUser(Long id){
        UserEntity user = usersRepository.getOne(id);
        user.setIsActive(0);
        usersRepository.save(user);
        return 1;
    }

    @Override
    public Integer login(UserDTO userDTO){
        UserEntity user = usersRepository.findByUserName(userDTO.getUserName());
        if(user != null && user.getPassWord().equals(passwordEncoder.encode(userDTO.getPassWord()))){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer logout(){
        return 1;
    }
}
