package BurmatovM.demostudy.dto;

import BurmatovM.demostudy.entity.Register;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;


    public Register toEntity() {
        Register register = new Register();
        register.setId(id);
        register.setLogin(login);
        register.setPassword(password);

        return register;
    }
}
