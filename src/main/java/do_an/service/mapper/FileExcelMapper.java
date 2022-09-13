package do_an.service.mapper;

import do_an.dto.FileExcelDTO;
import do_an.entity.FileExcelEntity;
import do_an.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileExcelMapper extends EntityMapper<FileExcelDTO, FileExcelEntity>{
}
