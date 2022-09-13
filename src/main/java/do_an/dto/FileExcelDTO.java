package do_an.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileExcelDTO {

    private Long id;

    private String fileName;

    private String filePath;

    private String fileType;

    private Integer isActive;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath=" + filePath +
                ", fileType=" + fileType +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
