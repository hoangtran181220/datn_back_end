package do_an.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "synthetic")
@AllArgsConstructor
@NoArgsConstructor
public class SyntheticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_NV")
    private Long idNv;

    @Column(name = "THU")
    private Long thu;

    @Column(name = "CHI")
    private Long chi;
}
