package BurmatovM.demostudy.controller;


import BurmatovM.demostudy.entity.CatEntity;
import BurmatovM.demostudy.repository.CatRepository;
import BurmatovM.demostudy.repository.RegisterRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final CatRepository catRepository;
    private final RegisterRepository registerRepository;

    @GetMapping
    public String reg() {
        return "profile";
    }

    @ModelAttribute("userCats")
    public List<CatEntity> userCats(HttpServletRequest request) throws SQLException {
        final String dir = System.getProperty("user.dir");

        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        String cookieName = "id";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    var idInt = Long.parseLong(c.getValue());
                    var user = registerRepository.findById(idInt).orElse(null);
                    return user.getCats();
                }
            }
        }

        return null;
    }

    @GetMapping("/sell")
    public String buy(String val) throws SQLException {//Map<String, Object> req){
        System.out.println(val);
        var id = Integer.parseInt(val);
        //DBUtils.buyCat(id);
        var res = catRepository.findById(new Long(id)).orElse(null);

        res.setIdOwner(null);
        catRepository.save(res);

        return "redirect:/";
    }
}
