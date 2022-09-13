package do_an.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASS_WORD")
    private String passWord;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private Long role;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;

}
