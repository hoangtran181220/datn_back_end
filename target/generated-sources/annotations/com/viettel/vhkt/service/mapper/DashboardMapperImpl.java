package com.viettel.vhkt.service.mapper;

import com.viettel.vhkt.dto.DashboardDTO;
import com.viettel.vhkt.entity.DashboardEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T10:47:17+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class DashboardMapperImpl implements DashboardMapper {

    @Override
    public DashboardEntity toEntity(DashboardDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DashboardEntity dashboardEntity = new DashboardEntity();

        dashboardEntity.setId( dto.getId() );
        dashboardEntity.setIdNv( dto.getIdNv() );
        dashboardEntity.setTongThu( dto.getTongThu() );
        dashboardEntity.setTongChi( dto.getTongChi() );
        dashboardEntity.setNgay( dto.getNgay() );
        dashboardEntity.setIsActive( dto.getIsActive() );

        return dashboardEntity;
    }

    @Override
    public DashboardDTO toDto(DashboardEntity entity) {
        if ( entity == null ) {
            return null;
        }

        DashboardDTO dashboardDTO = new DashboardDTO();

        dashboardDTO.setId( entity.getId() );
        dashboardDTO.setIdNv( entity.getIdNv() );
        dashboardDTO.setTongThu( entity.getTongThu() );
        dashboardDTO.setTongChi( entity.getTongChi() );
        dashboardDTO.setNgay( entity.getNgay() );
        dashboardDTO.setIsActive( entity.getIsActive() );

        return dashboardDTO;
    }

    @Override
    public List<DashboardEntity> toEntity(List<DashboardDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DashboardEntity> list = new ArrayList<DashboardEntity>( dtoList.size() );
        for ( DashboardDTO dashboardDTO : dtoList ) {
            list.add( toEntity( dashboardDTO ) );
        }

        return list;
    }

    @Override
    public List<DashboardDTO> toDto(List<DashboardEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DashboardDTO> list = new ArrayList<DashboardDTO>( entityList.size() );
        for ( DashboardEntity dashboardEntity : entityList ) {
            list.add( toDto( dashboardEntity ) );
        }

        return list;
    }
}
