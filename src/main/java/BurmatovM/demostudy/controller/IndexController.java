package BurmatovM.demostudy.controller;

import BurmatovM.demostudy.DBUtils;
import BurmatovM.demostudy.entity.CatEntity;
import BurmatovM.demostudy.models.Cat;
import BurmatovM.demostudy.repository.CatRepository;
import BurmatovM.demostudy.repository.RegisterRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor

public class IndexController {

    private final CatRepository catRepository;
    private final RegisterRepository registerRepository;



    @GetMapping("/")
    public String index(Map<String, String> model, HttpServletRequest request, HttpServletResponse response
    ) throws SQLException {//){


        //catRepository.deleteAll();
        //catRepository.save(new CatEntity(new Long(1),"Michael","Mark","Best Cat","0", 0));
        //catRepository.save(new CatEntity(new Long(2),"Igor","Vitaliy","Bigger","0", 0));
        //catRepository.save(new CatEntity(new Long(3),"Alex","Chernish","Best Cat","0", 0));
        //catRepository.save(new CatEntity(new Long(4),"Michael","Barsik","Nice","0", 0));
        //catRepository.save(new CatEntity(new Long(5),"Michael","Hvost","Best Cat","0", 0));
        //catRepository.save(new CatEntity(new Long(6),"Michael","Garfild","Best Cat","0", 0));



       //for(var cat : DBUtils.requestAllCats()){
       //    model.put("" + cat.getId(), cat);
       //}
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        String cookieName = "id";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    var idInt = Long.parseLong(c.getValue());
                    var user = registerRepository.findById(idInt).orElse(null);
                    if(user == null)
                        return "redirect:/login";
                    model.put("userName", user.getLogin());

                    return "index";
                }
            }
        }


        //model.put("key3", 1552);
        return "redirect:/login";
    }

    @ModelAttribute("messages")
    public List<CatEntity> messages() throws SQLException {
        final String dir = System.getProperty("user.dir");
        List<CatEntity> list = null;
        list = catRepository.findAll();
       //        DBUtils.requestAllCats().stream().toList();
       //list.forEach(x-> x.setImageString("file:///" + (dir + "\\img\\"+ x.getId() + ".png").replace("\\", "/")));
       list.forEach(x-> x.setImageString(("\\img\\"+ x.getIdImage() + ".png").replace("\\", "/")));
       list.forEach(x-> System.out.println(x.getImageString()));
       list = list.stream().filter(x-> x.getIdOwner() == null).toList();
        return list;
    }



    @GetMapping("/check")
    public String check(String val) throws SQLException {//Map<String, Object> req){
        System.out.println(val);

        var list = DBUtils.requestAllCats();
        list.forEach(x-> x.setBuy(0));

        list.forEach(x-> {
            try {
                DBUtils.sellCat(x);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        return "index";
    }

    @GetMapping("/buy")
    public String buy(String val, HttpServletRequest request) throws SQLException {//Map<String, Object> req){
        System.out.println(val);
        var id = Integer.parseInt(val);
        //DBUtils.buyCat(id);
        var res = catRepository.findById(new Long(id)).orElse(null);

        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        String cookieName = "id";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    var idInt = Long.parseLong(c.getValue());
                    var user = registerRepository.findById(idInt).orElse(null);

                    res.setIdOwner(idInt);
                    res.setOwner(user.getLogin());

                    catRepository.save(res);
                }
            }
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {//Map<String, Object> req){
        eraseCookie(req, resp);

        return "redirect:/";
    }

    private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
    }
}
