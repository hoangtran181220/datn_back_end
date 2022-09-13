package do_an.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "dasboard")
@AllArgsConstructor
@NoArgsConstructor
public class DashboardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_NV")
    private Long idNv;

    @Column(name = "TONG_THU")
    private Long tongThu;

    @Column(name = "TONG_CHI")
    private Long tongChi;

    @Column(name = "NGAY")
    private Date ngay;

    @Column(name = "IS_ACTIVE")
    private In isActive;
}
