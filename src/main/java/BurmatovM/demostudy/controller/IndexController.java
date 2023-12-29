package BurmatovM.demostudy.controller;

import BurmatovM.demostudy.DBUtils;
import BurmatovM.demostudy.models.Cat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("")
public class IndexController {
    @GetMapping("/")
    public String index(Map<String, Cat> model) throws SQLException {//){

       //for(var cat : DBUtils.requestAllCats()){
       //    model.put("" + cat.getId(), cat);
       //}



        //model.put("key3", 1552);
        return "index";
    }

    @ModelAttribute("messages")
    public List<Cat> messages() throws SQLException {
        final String dir = System.getProperty("user.dir");
        var list = DBUtils.requestAllCats().stream().toList();
        //list.forEach(x-> x.setImageString("file:///" + (dir + "\\img\\"+ x.getId() + ".png").replace("\\", "/")));
        list.forEach(x-> x.setImageString(("\\img\\"+ x.getId() + ".png").replace("\\", "/")));
        list.forEach(x-> System.out.println(x.getImageString()));
        list = list.stream().filter(x-> x.isBuy() != 1).toList();
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
    public String buy(String val) throws SQLException {//Map<String, Object> req){
        System.out.println(val);
        var id = Integer.parseInt(val);
        DBUtils.buyCat(id);

        return "index";
    }
}
