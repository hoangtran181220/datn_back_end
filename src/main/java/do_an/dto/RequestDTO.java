package do_an.dto;

import do_an.dto.FileExcelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private Long id;

    private String code;

    private String title;

    private Long idNv;

    private String nameNvYc;

    private String emailNvYc;

    private Long idExcel;

    private Date timeSendYc;

    private Integer status;

    private Integer isActive;

    private String fileExcelName;

    private FileExcelDTO fileExcelDTO;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title=" + title +
                ", idNv='" + idNv + '\'' +
                ", nameNvYc=" + nameNvYc +
                ", emailNvYc=" + emailNvYc +
                ", idExcel='" + idExcel + '\'' +
                ", timeSendYc='" + timeSendYc + '\'' +
                ", status='" + status + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
