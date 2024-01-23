package BurmatovM.demostudy.controller;

import BurmatovM.demostudy.dto.RegisterDTO;
import BurmatovM.demostudy.dto.RegisterFormDTO;
import BurmatovM.demostudy.entity.Register;
import BurmatovM.demostudy.repository.CatRepository;
import BurmatovM.demostudy.repository.RegisterRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController
{
    @Autowired
    private final RegisterRepository registerRepository;
    @GetMapping
    public String reg() {
        return "reg2";
    }

    @PostMapping("/try")
    public String check(@Valid RegisterFormDTO register, Model model, Error errors, HttpServletResponse response) {

        var mod = register.toEntity();

        var all = registerRepository.findAll();
        System.out.println(all);
        var res = all.stream().filter(x->!x.getLogin().equals(mod.getLogin())).findFirst().orElse(null);
        System.out.println(res);

        if(res == null)
            return "redirect:/register";

        var saved = registerRepository.save(mod);
        return "redirect:/login";
    }
}
