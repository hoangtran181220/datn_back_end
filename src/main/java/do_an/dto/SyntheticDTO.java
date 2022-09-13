package do_an.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyntheticDTO {
    private Long id;

    private Long idNv;

    private Long thu;

    private Long chi;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", thu='" + thu + '\'' +
                ", chi=" + chi +
                ", idNv='" + idNv + '\'' +
                '}';
    }
}
