package sample;
import java.sql.*;
import java.util.Scanner;

public class Sql {
    public static void main(String[] args){
        try{
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            String name = in.next();
            int age = in.nextInt();
            Connection mySql = DriverManager.getConnection("jdbc:mysql://localhost:8889/Project","Dauren", "Dauren2701");
            Statement mySt = mySql.createStatement();
            ResultSet myRS = mySt.executeQuery("select * from students");
            String query = " insert into students (ID, Name, age)"
                    + " values (?, ?, ?)";
            PreparedStatement myPS = mySql.prepareStatement(query);
            myPS.setInt(1,id);
            myPS.setString(2,name);
            myPS.setInt(3,age);
            myPS.execute();
            while (myRS.next()){
                System.out.println(myRS.getString("ID") + " " + myRS.getString("Name") + " " + myRS.getString("age"));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
