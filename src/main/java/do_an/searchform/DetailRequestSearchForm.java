package do_an.searchform;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailRequestSearchForm {

    private String tenKhoanTien;

    private Long idNv;

    private Date thoiGian;
}
