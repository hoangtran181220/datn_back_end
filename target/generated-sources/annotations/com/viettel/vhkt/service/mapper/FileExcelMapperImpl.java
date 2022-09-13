package com.viettel.vhkt.service.mapper;

import com.viettel.vhkt.dto.FileExcelDTO;
import com.viettel.vhkt.entity.FileExcelEntity;
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
public class FileExcelMapperImpl implements FileExcelMapper {

    @Override
    public FileExcelEntity toEntity(FileExcelDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FileExcelEntity fileExcelEntity = new FileExcelEntity();

        fileExcelEntity.setId( dto.getId() );
        fileExcelEntity.setFileName( dto.getFileName() );
        fileExcelEntity.setFilePath( dto.getFilePath() );
        fileExcelEntity.setFileType( dto.getFileType() );
        fileExcelEntity.setIsActive( dto.getIsActive() );

        return fileExcelEntity;
    }

    @Override
    public FileExcelDTO toDto(FileExcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        FileExcelDTO fileExcelDTO = new FileExcelDTO();

        fileExcelDTO.setId( entity.getId() );
        fileExcelDTO.setFileName( entity.getFileName() );
        fileExcelDTO.setFilePath( entity.getFilePath() );
        fileExcelDTO.setFileType( entity.getFileType() );
        fileExcelDTO.setIsActive( entity.getIsActive() );

        return fileExcelDTO;
    }

    @Override
    public List<FileExcelEntity> toEntity(List<FileExcelDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<FileExcelEntity> list = new ArrayList<FileExcelEntity>( dtoList.size() );
        for ( FileExcelDTO fileExcelDTO : dtoList ) {
            list.add( toEntity( fileExcelDTO ) );
        }

        return list;
    }

    @Override
    public List<FileExcelDTO> toDto(List<FileExcelEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FileExcelDTO> list = new ArrayList<FileExcelDTO>( entityList.size() );
        for ( FileExcelEntity fileExcelEntity : entityList ) {
            list.add( toDto( fileExcelEntity ) );
        }

        return list;
    }
}
