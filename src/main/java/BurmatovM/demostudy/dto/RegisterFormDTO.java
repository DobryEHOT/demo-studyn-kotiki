package BurmatovM.demostudy.dto;

import BurmatovM.demostudy.entity.Register;
import BurmatovM.demostudy.entity.Register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterFormDTO {

    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 8, max = 24)
    private CharSequence password;


    public Register toEntity() {
        Register register = new Register();

        register.setLogin(username);
        register.setPassword(password.toString());

        return register;
    }

}