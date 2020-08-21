package xyz.tamanmain.authentication.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user")
public class User {

    @Id
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

}
