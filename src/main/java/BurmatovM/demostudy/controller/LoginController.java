package BurmatovM.demostudy.controller;

import BurmatovM.demostudy.dto.RegisterFormDTO;
import BurmatovM.demostudy.entity.CatEntity;
import BurmatovM.demostudy.repository.RegisterRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private final RegisterRepository registerRepository;

    @GetMapping
    public String log() {
        return "login";
    }

    @PostMapping("/try")
    public String check(@Valid RegisterFormDTO register, Model model, Error errors, HttpServletResponse response) {

        var mod = register.toEntity();

        var all = registerRepository.findAll();
        System.out.println(all);
        var res = all.stream().filter(x->x.getLogin().equals(mod.getLogin()) && x.getPassword().equals(mod.getPassword())).findFirst().orElse(null);
        System.out.println(res);

        if(res == null)
            return "redirect:/login";

        var coo = new jakarta.servlet.http.Cookie("id", res.getId().toString());
        coo.setPath("/");
        response.addCookie(coo);
        return "redirect:/";
    }


}
