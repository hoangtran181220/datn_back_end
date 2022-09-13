package do_an.service.mapper;

import do_an.dto.RequestDTO;
import do_an.entity.RequestEntity;
import do_an.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper extends EntityMapper<RequestDTO, RequestEntity> {
}
