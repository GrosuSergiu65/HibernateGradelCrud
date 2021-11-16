package md.hibernate.practice.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "user_data", schema = "public", catalog = "HibernateSource")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String username;

    @Column(name = "userpassword", nullable = false, length = -1)
    private String password;

}
