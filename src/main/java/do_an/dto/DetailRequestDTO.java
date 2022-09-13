package do_an.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRequestDTO {

    private Long id;

    private String tenKhoanTien;

    private Long soTien;

    private Integer type;

    private Long idNv;

    private Date thoiGian;

    private Integer status;

    private Integer isActive;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", tenKhoanTien='" + tenKhoanTien + '\'' +
                ", soTien=" + soTien +
                ", idNv='" + idNv + '\'' +
                ", type=" + type +
                ", thoiGian='" + thoiGian + '\'' +
                ", status='" + status + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
