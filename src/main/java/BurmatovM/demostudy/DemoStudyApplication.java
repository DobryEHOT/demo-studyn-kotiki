package BurmatovM.demostudy;

import BurmatovM.demostudy.models.Cat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static BurmatovM.demostudy.DBUtils.*;

@SpringBootApplication
public class DemoStudyApplication  {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//Class.forName ("com.mysql.cj.jdbc.Driver");

		//addCat(new Cat(0,"Michael","Mark","Best Cat","0", 0));
		//addCat(new Cat(0,"Igor","Vitaliy","Bigger","0", 0));
		//addCat(new Cat(0,"Alex","Chernish","Best Cat","0", 0));
		//addCat(new Cat(0,"Michael","Barsik","Nice","0", 0));
		//addCat(new Cat(0,"Michael","Hvost","Best Cat","0", 0));
		//addCat(new Cat(0,"Michael","Garfild","Best Cat","0", 0));
		//var allCats = requestAllCats();
		//for (var cat : allCats) {
		//	System.out.println(cat);
		//	buyCat(cat);
		//}

		//System.exit(1);
		System.setProperty("server.port", "8081");
		SpringApplication.run(DemoStudyApplication.class, args);
	}
}
