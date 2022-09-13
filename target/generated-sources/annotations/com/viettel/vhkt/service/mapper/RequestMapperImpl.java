package com.viettel.vhkt.service.mapper;

import com.viettel.vhkt.dto.RequestDTO;
import com.viettel.vhkt.entity.RequestEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T10:47:16+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class RequestMapperImpl implements RequestMapper {

    @Override
    public RequestEntity toEntity(RequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RequestEntity requestEntity = new RequestEntity();

        requestEntity.setId( dto.getId() );
        requestEntity.setCode( dto.getCode() );
        requestEntity.setTitle( dto.getTitle() );
        requestEntity.setIdNv( dto.getIdNv() );
        requestEntity.setNameNvYc( dto.getNameNvYc() );
        requestEntity.setEmailNvYc( dto.getEmailNvYc() );
        requestEntity.setIdExcel( dto.getIdExcel() );
        requestEntity.setTimeSendYc( dto.getTimeSendYc() );
        requestEntity.setStatus( dto.getStatus() );
        requestEntity.setIsActive( dto.getIsActive() );

        return requestEntity;
    }

    @Override
    public RequestDTO toDto(RequestEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setId( entity.getId() );
        requestDTO.setCode( entity.getCode() );
        requestDTO.setTitle( entity.getTitle() );
        requestDTO.setIdNv( entity.getIdNv() );
        requestDTO.setNameNvYc( entity.getNameNvYc() );
        requestDTO.setEmailNvYc( entity.getEmailNvYc() );
        requestDTO.setIdExcel( entity.getIdExcel() );
        requestDTO.setTimeSendYc( entity.getTimeSendYc() );
        requestDTO.setStatus( entity.getStatus() );
        requestDTO.setIsActive( entity.getIsActive() );

        return requestDTO;
    }

    @Override
    public List<RequestEntity> toEntity(List<RequestDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<RequestEntity> list = new ArrayList<RequestEntity>( dtoList.size() );
        for ( RequestDTO requestDTO : dtoList ) {
            list.add( toEntity( requestDTO ) );
        }

        return list;
    }

    @Override
    public List<RequestDTO> toDto(List<RequestEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RequestDTO> list = new ArrayList<RequestDTO>( entityList.size() );
        for ( RequestEntity requestEntity : entityList ) {
            list.add( toDto( requestEntity ) );
        }

        return list;
    }
}
