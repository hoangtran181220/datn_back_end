package do_an.searchform;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestSearchForm {

    private String title;

    private String code;

    private String nameNvYc;
}
