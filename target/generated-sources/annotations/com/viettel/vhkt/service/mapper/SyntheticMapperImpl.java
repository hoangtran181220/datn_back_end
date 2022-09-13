package com.viettel.vhkt.service.mapper;

import com.viettel.vhkt.dto.SyntheticDTO;
import com.viettel.vhkt.entity.SyntheticEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T14:22:08+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class SyntheticMapperImpl implements SyntheticMapper {

    @Override
    public SyntheticEntity toEntity(SyntheticDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SyntheticEntity syntheticEntity = new SyntheticEntity();

        syntheticEntity.setId( dto.getId() );
        syntheticEntity.setIdNv( dto.getIdNv() );
        syntheticEntity.setThu( dto.getThu() );
        syntheticEntity.setChi( dto.getChi() );

        return syntheticEntity;
    }

    @Override
    public SyntheticDTO toDto(SyntheticEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SyntheticDTO syntheticDTO = new SyntheticDTO();

        syntheticDTO.setId( entity.getId() );
        syntheticDTO.setIdNv( entity.getIdNv() );
        syntheticDTO.setThu( entity.getThu() );
        syntheticDTO.setChi( entity.getChi() );

        return syntheticDTO;
    }

    @Override
    public List<SyntheticEntity> toEntity(List<SyntheticDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SyntheticEntity> list = new ArrayList<SyntheticEntity>( dtoList.size() );
        for ( SyntheticDTO syntheticDTO : dtoList ) {
            list.add( toEntity( syntheticDTO ) );
        }

        return list;
    }

    @Override
    public List<SyntheticDTO> toDto(List<SyntheticEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SyntheticDTO> list = new ArrayList<SyntheticDTO>( entityList.size() );
        for ( SyntheticEntity syntheticEntity : entityList ) {
            list.add( toDto( syntheticEntity ) );
        }

        return list;
    }
}
