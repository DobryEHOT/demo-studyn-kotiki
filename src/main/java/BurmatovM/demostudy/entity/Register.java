package BurmatovM.demostudy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@jakarta.persistence.Entity
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    @OneToMany
    @JoinColumn(name = "idOwner")
    private List<CatEntity> cats;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
