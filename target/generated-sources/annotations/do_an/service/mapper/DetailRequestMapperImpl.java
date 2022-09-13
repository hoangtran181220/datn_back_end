package do_an.service.mapper;

import do_an.dto.DetailRequestDTO;
import do_an.entity.DetailRequestEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-09T16:56:52+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class DetailRequestMapperImpl implements DetailRequestMapper {

    @Override
    public DetailRequestEntity toEntity(DetailRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DetailRequestEntity detailRequestEntity = new DetailRequestEntity();

        detailRequestEntity.setId( dto.getId() );
        detailRequestEntity.setTenKhoanTien( dto.getTenKhoanTien() );
        detailRequestEntity.setSoTien( dto.getSoTien() );
        detailRequestEntity.setType( dto.getType() );
        detailRequestEntity.setIdNv( dto.getIdNv() );
        detailRequestEntity.setThoiGian( dto.getThoiGian() );
        detailRequestEntity.setStatus( dto.getStatus() );
        detailRequestEntity.setIsActive( dto.getIsActive() );

        return detailRequestEntity;
    }

    @Override
    public DetailRequestDTO toDto(DetailRequestEntity entity) {
        if ( entity == null ) {
            return null;
        }

        DetailRequestDTO detailRequestDTO = new DetailRequestDTO();

        detailRequestDTO.setId( entity.getId() );
        detailRequestDTO.setTenKhoanTien( entity.getTenKhoanTien() );
        detailRequestDTO.setSoTien( entity.getSoTien() );
        detailRequestDTO.setType( entity.getType() );
        detailRequestDTO.setIdNv( entity.getIdNv() );
        detailRequestDTO.setThoiGian( entity.getThoiGian() );
        detailRequestDTO.setStatus( entity.getStatus() );
        detailRequestDTO.setIsActive( entity.getIsActive() );

        return detailRequestDTO;
    }

    @Override
    public List<DetailRequestEntity> toEntity(List<DetailRequestDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DetailRequestEntity> list = new ArrayList<DetailRequestEntity>( dtoList.size() );
        for ( DetailRequestDTO detailRequestDTO : dtoList ) {
            list.add( toEntity( detailRequestDTO ) );
        }

        return list;
    }

    @Override
    public List<DetailRequestDTO> toDto(List<DetailRequestEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DetailRequestDTO> list = new ArrayList<DetailRequestDTO>( entityList.size() );
        for ( DetailRequestEntity detailRequestEntity : entityList ) {
            list.add( toDto( detailRequestEntity ) );
        }

        return list;
    }
}
