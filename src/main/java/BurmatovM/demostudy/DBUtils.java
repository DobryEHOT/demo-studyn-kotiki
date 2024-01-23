package BurmatovM.demostudy;

import BurmatovM.demostudy.models.Cat;

import java.sql.*;
import java.util.ArrayList;

public class DBUtils {

    public static void initTables() throws SQLException {

        try{

            String url = "jdbc:mysql://51.250.108.9:3306/javaDB";
            Connection connection = DriverManager.getConnection(url,"monty","some_pass");
            Statement statement = connection.createStatement();
            statement.execute("""
                    DROP TABLE Cats;
                    CREATE TABLE Cats
                    (
                        id INTEGER,
                        owner TEXT,
                        name TEXT,
                        descr TEXT,
                        image TEXT,
                        is_buy INTEGER,
                        PRIMARY KEY(id)
                    );
                    """
            );

            statement.close();
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }

    public static void addCat(Cat cat) throws SQLException {

        try{
            String url = "jdbc:mysql://51.250.108.9:3306/javaDB";
            Connection connection = DriverManager.getConnection(url,"monty","some_pass");

            Statement statement = connection.createStatement();
            String Query_String ="INSERT INTO Cats (owner, descr, name, image, is_buy) VALUES ('"+cat.getOwner()+"', '"+cat.getDescr()+ "', '"+cat.getName()+"', '"+cat.getImageString()+"', '0')";
            statement.execute(Query_String);

            statement.close();
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public static ArrayList<Cat> requestAllCats() throws SQLException {
        var result = new ArrayList<Cat>();
        try{
            String url = "jdbc:mysql://51.250.108.9:3306/javaDB";
            Connection connection = DriverManager.getConnection(url,"monty","some_pass");

            Statement statement = connection.createStatement();
            String Query_String ="SELECT * FROM Cats";
            ResultSet resultSet = statement.executeQuery(Query_String);

            while(resultSet.next()){
                result.add(new Cat(
                        resultSet.getInt("id"),
                        resultSet.getString("owner"),
                        resultSet.getString("name"),
                        resultSet.getString("descr"),
                        resultSet.getString("image"),
                        resultSet.getInt("is_buy")
                ));
            }

            statement.close();
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
        finally {
            return result;
        }
    }

    public static void buyCat(Cat cat) throws SQLException {

        try{
            String url = "jdbc:mysql://51.250.108.9:3306/javaDB";
            Connection connection = DriverManager.getConnection(url,"monty","some_pass");

            Statement statement = connection.createStatement();
            String Query_String ="UPDATE Cats SET is_buy = 1 WHERE id = " + cat.getId();
            statement.execute(Query_String);

            statement.close();
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public static void buyCat(int id) throws SQLException {

        try{
            String url = "jdbc:mysql://51.250.108.9:3306/javaDB";
            Connection connection = DriverManager.getConnection(url,"monty","some_pass");

            Statement statement = connection.createStatement();
            String Query_String ="UPDATE Cats SET is_buy = 1 WHERE id = " + id;
            statement.execute(Query_String);

            statement.close();
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public static void sellCat(Cat cat) throws SQLException {

        try{
            String url = "jdbc:mysql://51.250.108.9:3306/javaDB";
            Connection connection = DriverManager.getConnection(url,"monty","some_pass");

            Statement statement = connection.createStatement();
            String Query_String ="UPDATE Cats SET is_buy = 0 WHERE id = " + cat.getId();
            statement.execute(Query_String);

            statement.close();
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
}
