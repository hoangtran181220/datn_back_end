package do_an.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ID_NV")
    private Long idNv;

    @Column(name = "NAME_NV_YC")
    private String nameNvYc;

    @Column(name = "EMAIL_NV_YC")
    private String emailNvYc;

    @Column(name = "ID_EXCEL")
    private Long idExcel;

    @Column(name = "TIME_SEND_YC")
    private Date timeSendYc;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    @Formula(value = "(select f.FILE_NAME from file_excel f where f.ID = ID_EXCEL)")
    private String excelFileName;
}
