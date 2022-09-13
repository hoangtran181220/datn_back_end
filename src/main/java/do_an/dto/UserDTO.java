package do_an.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String userName;

    private String passWord;

    private String email;

    private Long role;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password=" + passWord +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
