package do_an.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "detail_request")
@AllArgsConstructor
@NoArgsConstructor
public class DetailRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEN_KHOAN_TIEN")
    private String tenKhoanTien;

    @Column(name = "SO_TIEN")
    private Long soTien;

    @Column(name = "TYPE")
    private Integer type;

    @Column(name = "ID_NV")
    private Long idNv;

    @Column(name = "THOI_GIAN")
    private Date thoiGian;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;
}
