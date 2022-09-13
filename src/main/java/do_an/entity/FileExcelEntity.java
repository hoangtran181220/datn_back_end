package do_an.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "file_excel")
@AllArgsConstructor
@NoArgsConstructor
public class FileExcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;

}
