package do_an.service.mapper;

import do_an.dto.UserDTO;
import do_an.entity.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UsersMapper extends EntityMapper<UserDTO, UserEntity> {
}
