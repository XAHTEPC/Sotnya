package com.example.sotnya.View;

import com.example.sotnya.Front;
import com.example.sotnya.PaneModel.Employee;
import com.example.sotnya.PaneModel.Structure;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class LocalManagerFront {
    static Pane pane_scroll;
    public static Pane getStartFront() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url;
        Url = new FileInputStream("png/localmanager.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        Button structure = new Button();
        structure.setBackground(null);
        structure.setLayoutY(364);
        structure.setLayoutX(379);
        structure.setPrefSize(450,75);
        pane.getChildren().add(structure);
        structure.setOnAction(t ->{
            try {
                pane_scroll = Structure.getPane(true);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll, 1);
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
        employee.setLayoutY(264);
        employee.setPrefSize(450,75);
        pane.getChildren().add(employee);
        employee.setOnAction(t1 ->{
            try {
                pane_scroll = Employee.getPane(true, false, false);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,1);
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
