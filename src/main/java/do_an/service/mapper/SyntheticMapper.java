package do_an.service.mapper;

import do_an.dto.SyntheticDTO;
import do_an.entity.SyntheticEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SyntheticMapper extends EntityMapper<SyntheticDTO, SyntheticEntity> {
}
