package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.awt.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.*;
import javafx.scene.image.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Menu menu = new Menu();
        Scene scene = new Scene(menu.gridPane,500,750);

        Options options = new Options();
        Scene sceneOptions = new Scene(options.pane,500,500);

        InterfaceBurger burger = new InterfaceBurger();
        Scene sceneBurger = new Scene(burger.hBox, 500, 500);


        menu.login.setOnAction(event -> primaryStage.setScene(sceneOptions));
        options.bt1.setOnAction(event -> primaryStage.setScene(sceneBurger));
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {

        launch(args);
    }

}
class InterfaceBurger{
    HBox hBox;
    ArrayList<String> options = new ArrayList<>();
    InterfaceBurger() throws Exception{
        hBox = new HBox();
        options.add("Burger");
        options.add("CheeseBurger");
        options.add("Gumburger");
        Label label = new Label("Menu:");
        Button button;
        hBox.getChildren().add(label);
        for (int i = 0; i<options.size(); i++){
            button = new Button(options.get(i));
            hBox.getChildren().addAll(button);
        }
    }
}
class Client{
    String name;
    String password;

    public Client(String name, String password){
        this.password = password;
        this.name = name;
    }

    public Client() {

    }

    String getName() {

        return name;
    }

     void setName(String name) {

        this.name = name;
    }

     String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

class Options{
    ArrayList<String> options = new ArrayList<>();
    VBox pane;
    Button bt1;
    Button back = new Button("Back");
    Options() throws Exception{
        pane = new VBox(30);
        pane.setAlignment(Pos.CENTER);
        Label label = new Label("What do you want?");
        options.add("Burger");
        options.add("Pizza");
        options.add("Drink");
        options.add("Dessert");
        pane.getChildren().add(label);
        for (int i = 0; i<options.size(); i++){
            bt1 = new Button(options.get(i));
            pane.getChildren().addAll(bt1);
        }

    }
}
abstract class Choice{
    ArrayList<String> options = new ArrayList<>();
    abstract ArrayList<String> getOptions();

    abstract void setOptions(ArrayList<String> options);

}
class Menu{
    GridPane gridPane;
    Button login;
    Menu() throws Exception{
        Client client = new Client();
 
        gridPane = new GridPane();
        FileInputStream fileInputStream = new FileInputStream("pizza.jpg");
        Image image = new Image(fileInputStream);
        BackgroundImage bi = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background back = new Background(bi);
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setHgap(5.5);
        gridPane.setVgap(5.5);
        gridPane.setBackground(back);
        gridPane.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Welcome to IITU'pizza");

        login = new Button("Log in");
        nameLabel.setFont(Font.font("Alice", FontWeight.BOLD,30));
        nameLabel.setTextFill(Color.BLACK);
        TextField textFieldName = new TextField();
        TextField textFieldPassword = new TextField();

        try{
            Scanner in = new Scanner(System.in);

            String textName = in.next();
            String textPassword = in.next();
            String name = client.getName();
            String password = textPassword;
            Connection mySql = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza","aldiyar", "aldiyar7050");
            Statement mySt = mySql.createStatement();
            ResultSet myRS = mySt.executeQuery("select * from clients");
            String query = " insert into clients (name, password)"
                    + " values (?, ?)";
            PreparedStatement myPS = mySql.prepareStatement(query);
            myPS.setString(1,name);
            myPS.setString(2,password);

            myPS.execute();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        gridPane.add(nameLabel,1,0);
        gridPane.add(textFieldName,1,2);
        gridPane.add(textFieldPassword,1,3);
        gridPane.add(login, 1,4);
    }
}



