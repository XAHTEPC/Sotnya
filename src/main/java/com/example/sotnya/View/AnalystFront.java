package com.example.sotnya.View;

import com.example.sotnya.Front;
import com.example.sotnya.PaneModel.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class AnalystFront {
    static Pane pane_scroll;
    public static Pane getStartFront() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url;
        Url = new FileInputStream("png/analyst.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        Button client = new Button();
        client.setBackground(null);
        client.setLayoutX(379);
        client.setLayoutY(492);
        client.setPrefSize(450,75);
        pane.getChildren().add(client);
        client.setOnAction(t ->{
            try {
                pane_scroll = Client.getPane(false, false, false);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,2);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button visit = new Button();
        visit.setBackground(null);
        visit.setLayoutX(379);
        visit.setLayoutY(292);
        visit.setPrefSize(450,75);
        pane.getChildren().add(visit);
        visit.setOnAction(t1 ->{
            try {
                pane_scroll = Visit.getPane(false);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,2);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button structure = new Button();
        structure.setBackground(null);
        structure.setLayoutY(192);
        structure.setLayoutX(379);
        structure.setPrefSize(450,75);
        pane.getChildren().add(structure);
        structure.setOnAction(t ->{
            try {
                pane_scroll = Structure.getPane(false);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,2);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button employee = new Button();
        employee.setBackground(null);
        employee.setLayoutX(379);
        employee.setLayoutY(92);
        employee.setPrefSize(450,75);
        pane.getChildren().add(employee);
        employee.setOnAction(t1 ->{
            try {
                pane_scroll = Employee.getPane( false, true, false);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,2);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button service = new Button();
        service.setBackground(null);
        service.setLayoutX(379);
        service.setLayoutY(392);
        service.setPrefSize(450,75);
        pane.getChildren().add(service);
        service.setOnAction(t1 ->{
            try {
                pane_scroll = Service.getPane();
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,2);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Button exit = new Button();
        exit.setBackground(null);
        exit.setLayoutX(1066);
        exit.setLayoutY(15);
        exit.setPrefSize(58,59);
        pane.getChildren().add(exit);
        exit.setOnAction(t ->{
            try {
                Front.exit();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return pane;
    }
}
