package com.example.sotnya;

import com.example.sotnya.DataBase.Postgre;
import com.example.sotnya.View.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Front  extends Application {
    public static String password = "";
    public static String login ="";
    public static String lvl ="";

    public static Pane pane;
    public static Group root;
    public static Scene scene;


    @Override
    public void start(Stage stage) throws FileNotFoundException {
        root = new Group();
        scene = new Scene(root, 1200, 800);
        stage.initStyle(StageStyle.DECORATED);
        pane = IntroFront.getStartFront();
        root.getChildren().add(pane);
        stage.setTitle("Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ParseException {
        launch();
    }

    public static void login(String pass, String login) throws SQLException, FileNotFoundException {
        System.out.println("login:" + login);
        System.out.println("pass:" + pass);
        Postgre postgre = new Postgre(login,pass);
        Front.login = login;
        Front.password = pass;
        Front.lvl = postgre.getRole();
        System.out.println(Front.lvl);
        System.out.println("OK");
        if(Front.lvl.equals("Local_manager")){
            root.getChildren().remove(pane);
            pane = LocalManagerFront.getStartFront();
            root.getChildren().add(pane);
        }
        else if (Front.lvl.equals("Administrator")) {
            root.getChildren().remove(pane);
            pane = AdminFront.getStartFront();
            root.getChildren().add(pane);
        }
        else if (Front.lvl.equals("Staff")){
            root.getChildren().remove(pane);
            pane = StaffFront.getStartFront();
            root.getChildren().add(pane);
        }
        else if (Front.lvl.equals("Manager")){
            root.getChildren().remove(pane);
            pane = ManagerFront.getStartFront();
            root.getChildren().add(pane);
        }
        else if (Front.lvl.equals("Analyst")){
            root.getChildren().remove(pane);
            pane = AnalystFront.getStartFront();
            root.getChildren().add(pane);
        }
    }
    public static void exit() throws FileNotFoundException {
        root.getChildren().remove(pane);
        pane = IntroFront.getStartFront();
        root.getChildren().add(pane);
    }
}

