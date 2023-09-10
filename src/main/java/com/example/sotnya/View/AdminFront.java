package com.example.sotnya.View;

import com.example.sotnya.DataBase.Postgre;
import com.example.sotnya.Front;
import com.example.sotnya.PaneModel.Employee;
import com.example.sotnya.PaneModel.Structure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class AdminFront {
    static Pane pane_scroll;
    public static Pane getStartFront() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url;
        Url = new FileInputStream("png/admin.png");
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
                Front.pane = ScrollFront.getStartFront(pane_scroll,5);
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

        Button employee = new Button();
        employee.setBackground(null);
        employee.setLayoutX(379);
        employee.setLayoutY(264);
        employee.setPrefSize(450,75);
        pane.getChildren().add(employee);
        employee.setOnAction(t1 ->{
            try {
                pane_scroll = Employee.getPane2(true, false, true);
                Front.root.getChildren().remove(Front.pane);
                Front.pane = ScrollFront.getStartFront(pane_scroll,5);
                Front.root.getChildren().add(Front.pane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Button addStructure = new Button();
        addStructure.setBackground(null);
        addStructure.setLayoutX(841);
        addStructure.setLayoutY(373);
        addStructure.setPrefSize(57,59);
        pane.getChildren().add(addStructure);
        addStructure.setOnAction(t1 -> {
            try {
                Structure.addStructure();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button addEmployee = new Button();
        addEmployee.setBackground(null);
        addEmployee.setLayoutX(841);
        addEmployee.setLayoutY(273);
        addEmployee.setPrefSize(57,59);
        pane.getChildren().add(addEmployee);
        addEmployee.setOnAction(t1 -> {
            try {
                Employee.addEmployee();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button addWork = new Button();
        addWork.setLayoutY(464);
        addWork.setLayoutX(379);
        addWork.setPrefSize(450,74);
        addWork.setBackground(null);
        pane.getChildren().add(addWork);
        addWork.setOnAction(t1 ->{
            Group root_add = new Group();
            Scene scene_add = new Scene(root_add, 410, 210);
            Stage newWindow = new Stage();
            newWindow.initStyle(StageStyle.DECORATED);
            Pane pane1 = new Pane();
            pane1.setPrefSize(410,210);
            pane1.setLayoutX(0);
            pane1.setLayoutY(0);
            root_add.getChildren().addAll(pane1);

            FileInputStream Url1;

            try {
                Url1 = new FileInputStream("png/addWork.png");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            Image url1 = new Image(Url1);
            ImageView front1 = new ImageView(url1);
            front1.setX(0);
            front1.setY(0);
            pane1.getChildren().add(front1);


            String[] mas = new String[0];
            try {
                mas = Postgre.getEmployee_name();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ObservableList<String> employee1 = FXCollections.observableArrayList(mas);
            ComboBox<String> comboBox3 = new ComboBox<String>(employee1);
            comboBox3.setMaxWidth(215);
            comboBox3.setMinWidth(215);
            comboBox3.setBackground(null);
            comboBox3.setLayoutX(180);
            comboBox3.setLayoutY(70);

            String[] mas1 = new String[0];
            try {
                mas1 = Postgre.getStructure_name();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ObservableList<String> work = FXCollections.observableArrayList(mas1);
            ComboBox<String> comboBox2 = new ComboBox<String>(work);
            comboBox2.setMaxWidth(215);
            comboBox2.setMinWidth(215);
            comboBox2.setBackground(null);
            comboBox2.setLayoutX(180);
            comboBox2.setLayoutY(114);

            Button save = new Button();
            save.setLayoutX(125);
            save.setLayoutY(150);
            save.setBackground(null);
            save.setPrefSize(150,32);
            root_add.getChildren().addAll(save,comboBox2,comboBox3);
            save.setOnAction(t2 ->{
                String w1 = comboBox3.getSelectionModel().getSelectedItem();
                String w2 = comboBox2.getSelectionModel().getSelectedItem();
                try {
                    Postgre.addWork(w1,w2);
                    Pane pane2 = Employee.getPane2(true,true,true);
                    Front.root.getChildren().remove(Front.pane);
                    Front.pane = ScrollFront.getStartFront(pane_scroll,5);
                    Front.root.getChildren().add(Front.pane);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                newWindow.close();
            });
            newWindow.setTitle("Добавление сотрудника");
            newWindow.setScene(scene_add);
            newWindow.show();
        });


        return pane;
    }
}

