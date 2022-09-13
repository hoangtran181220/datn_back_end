package do_an.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {

    private Long id;

    private Long idNv;

    private Long tongThu;

    private Long tongChi;

    private Date ngay;

    private In isActive;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", tongThu='" + tongThu + '\'' +
                ", tongChi=" + tongChi +
                ", idNv='" + idNv + '\'' +
                ", ngay=" + ngay +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
