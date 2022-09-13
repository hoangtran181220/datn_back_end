package do_an.service.mapper;

import do_an.dto.DetailRequestDTO;
import do_an.entity.DetailRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailRequestMapper extends EntityMapper<DetailRequestDTO, DetailRequestEntity> {
}
