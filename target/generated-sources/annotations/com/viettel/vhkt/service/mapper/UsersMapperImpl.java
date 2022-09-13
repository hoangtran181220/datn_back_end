package com.viettel.vhkt.service.mapper;

import com.viettel.vhkt.dto.UserDTO;
import com.viettel.vhkt.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T11:35:10+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class UsersMapperImpl implements UsersMapper {

    @Override
    public UserEntity toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setUserName( dto.getUserName() );
        userEntity.setPassWord( dto.getPassWord() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setRole( dto.getRole() );

        return userEntity;
    }

    @Override
    public UserDTO toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( entity.getId() );
        userDTO.setUserName( entity.getUserName() );
        userDTO.setPassWord( entity.getPassWord() );
        userDTO.setEmail( entity.getEmail() );
        userDTO.setRole( entity.getRole() );

        return userDTO;
    }

    @Override
    public List<UserEntity> toEntity(List<UserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dtoList.size() );
        for ( UserDTO userDTO : dtoList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> toDto(List<UserEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( entityList.size() );
        for ( UserEntity userEntity : entityList ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }
}
