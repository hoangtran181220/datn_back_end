package do_an.service.mapper;

import do_an.dto.DashboardDTO;
import do_an.entity.DashboardEntity;
import do_an.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DashboardMapper extends EntityMapper<DashboardDTO, DashboardEntity>{
}
