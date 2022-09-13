package do_an.service;

import do_an.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO getUserInfo(Long id);

    Integer changePassword(UserDTO userDTO);

    Integer createUser(UserDTO userDTO);

    Integer deleteUser(Long id);

    Integer login(UserDTO userDTO);

    Integer logout();
}
