package com.example.sotnya.PaneModel;

import com.example.sotnya.DataBase.Postgre;
import com.example.sotnya.View.ScrollFront;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Visit {String id;
    String client_name;
    String structure_name;
    String service_name;
    String data;
    String employee_name;
    String summa;
    String points;
    static boolean flag;
    static String Name="Чистенько";
    static  String[] mas3={""};
    String type;
    public Visit(String id, String client_name, String structure_name,
                 String service_name, String data, String employee_name, String summa, String points, String type) {
        this.id = id;
        this.client_name = client_name;
        this.structure_name = structure_name;
        this.service_name = service_name;
        this.data = data;
        this.employee_name = employee_name;
        this.summa = summa;
        this.points = points;
        this.type = type;
    }

    public static Pane getPane(boolean fl) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Pane res = new Pane();
        flag = fl;
        Text num = new Text("#");
        num.setLayoutX(20);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana",13));

        Text cl = new Text("Клиент");
        cl.setLayoutX(70);
        cl.setLayoutY(60);
        cl.setFont(Font.font("Verdana",13));

        Text em = new Text("Сотрудник");
        em.setLayoutX(220);
        em.setLayoutY(60);
        em.setFont(Font.font("Verdana",13));

        Text ser = new Text("Услуга");
        ser.setLayoutX(360);
        ser.setLayoutY(60);
        ser.setFont(Font.font("Verdana",13));

        Text price = new Text("Цена");
        price.setLayoutX(460);
        price.setLayoutY(60);
        price.setFont(Font.font("Verdana",13));

        Text str = new Text("Заведение");
        str.setLayoutX(510);
        str.setLayoutY(60);
        str.setFont(Font.font("Verdana",13));

        Text point = new Text("Оценка");
        point.setLayoutX(600);
        point.setLayoutY(60);
        point.setFont(Font.font("Verdana",13));

        Text date = new Text("Дата");
        date.setLayoutX(680);
        date.setLayoutY(60);
        date.setFont(Font.font("Verdana",13));
        Visit[] mas = Postgre.getAllVisit_Staff();
        int u = 80;
        for(int i=0; i<mas.length;i++, u+=70){
            if(mas[i] == null)
                continue;
            TextArea num_text = new TextArea();
            num_text.setEditable(false);
            num_text.setText(mas[i].id);
            num_text.setLayoutX(10);
            num_text.setLayoutY(0 + u);
            num_text.setMaxHeight(40);
            num_text.setMaxWidth(30);
            num_text.setMinHeight(40);
            num_text.setMinWidth(30);

            TextArea cl_text = new TextArea();
            cl_text.setText(mas[i].client_name);
            cl_text.setEditable(false);
            cl_text.setLayoutX(50);
            cl_text.setLayoutY(0 + u);
            cl_text.setMaxHeight(40);
            cl_text.setMaxWidth(140);
            cl_text.setMinWidth(140);

            TextArea emp_text = new TextArea();
            emp_text.setText(mas[i].employee_name);
            emp_text.setEditable(false);
            emp_text.setLayoutX(200);
            emp_text.setLayoutY(0 + u);
            emp_text.setMaxHeight(40);
            emp_text.setMaxWidth(140);
            emp_text.setMinHeight(40);
            emp_text.setMinWidth(140);

            TextArea ser_text = new TextArea();
            ser_text.setText(mas[i].service_name);
            ser_text.setEditable(false);
            ser_text.setLayoutX(350);
            ser_text.setLayoutY(0 + u);
            ser_text.setMaxHeight(40);
            ser_text.setMaxWidth(100);
            ser_text.setMinHeight(40);
            ser_text.setMinWidth(100);

            TextArea price_text = new TextArea();
            price_text.setText(mas[i].summa);
            price_text.setEditable(false);
            price_text.setLayoutX(460);
            price_text.setLayoutY(0 + u);
            price_text.setMaxHeight(40);
            price_text.setMaxWidth(40);

            TextArea str_text = new TextArea();
            str_text.setText(mas[i].structure_name);
            str_text.setEditable(false);
            str_text.setLayoutX(510);
            str_text.setLayoutY(0 + u);
            str_text.setMaxHeight(40);
            str_text.setMaxWidth(80);
            str_text.setMinHeight(40);
            str_text.setMinWidth(80);

            TextArea point_text = new TextArea();
            point_text.setText(mas[i].points);
            point_text.setEditable(false);
            point_text.setLayoutX(600);
            point_text.setLayoutY(0 + u);
            point_text.setMaxHeight(40);
            point_text.setMaxWidth(40);
            point_text.setMinHeight(40);
            point_text.setMinWidth(40);

            TextArea data_text = new TextArea();
            data_text.setText(mas[i].data);
            data_text.setEditable(false);
            data_text.setLayoutX(650);
            data_text.setLayoutY(0 + u);
            data_text.setMaxHeight(40);
            data_text.setMaxWidth(80);
            data_text.setMinHeight(40);
            data_text.setMinWidth(80);

            FileInputStream Url = new FileInputStream("png/pen.png");
            Image url = new Image(Url);
            ImageView pen = new ImageView(url);

            Button edit = new Button();
            edit.setGraphic(pen);
            edit.setLayoutX(740);
            edit.setLayoutY(0+u);

            final String id = mas[i].id;
            if(flag) {
                res.getChildren().add(edit);
                edit.setOnAction(v -> {
                    try {
                        edit_pos(id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            res.getChildren().addAll(num_text,cl_text,emp_text,ser_text,price_text,str_text,point_text, data_text);
        }

        res.getChildren().addAll(num, cl, em, ser, price, str, point, date);
        return res;
    }
    static ObservableList<String> service = FXCollections.observableArrayList(mas3);
    static ComboBox<String> comboBox3;

    public static void edit_pos(String id) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        Visit visit = Postgre.getVisit_byID(id);
        String nameClient = Postgre.getClientName_byID(visit.client_name);
        Scene scene_add = new Scene(root_add, 418, 418);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.DECORATED);

        FileInputStream Url;
        Url = new FileInputStream("png/editVisit.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        root_add.getChildren().add(front);


        TextField name = new TextField();
        name.setText(nameClient);
        name.setBackground(null);
        name.setLayoutX(180);
        name.setLayoutY(70);
        name.setMaxHeight(32);
        name.setMaxWidth(215);
        name.setEditable(false);

        String[] mas1 = Postgre.getStructure_name();
        ObservableList<String> work = FXCollections.observableArrayList(mas1);
        ComboBox<String> comboBox2 = new ComboBox<String>(work);
        comboBox2.setValue(visit.structure_name);
        comboBox2.setMaxWidth(215);
        comboBox2.setMinWidth(215);
        comboBox2.setBackground(null);
        comboBox2.setLayoutX(180);
        comboBox2.setLayoutY(110);
        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Name = (comboBox2.getSelectionModel().getSelectedItem());
                System.out.println("NAme:"+Name);
                try {
                    mas3 = Postgre.getService_name(Name);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                service = FXCollections.observableArrayList(mas3);
                comboBox3.setItems(service);
            }
        });
        comboBox3 = new ComboBox<String>(service);
        comboBox3.setValue(visit.service_name);
        comboBox3.setMaxWidth(215);
        comboBox3.setMinWidth(215);
        comboBox3.setBackground(null);
        comboBox3.setLayoutX(180);
        comboBox3.setLayoutY(155);

        TextField DATA = new TextField();
        DATA.setText(visit.data);
        DATA.setLayoutX(180);
        DATA.setBackground(null);
        DATA.setLayoutY(200);
        DATA.setStyle("-fx-background-color: transparent;");
        DATA.setMaxHeight(32);
        DATA.setMaxWidth(215);

        TextField emp = new TextField();
        emp.setText(visit.employee_name);
        emp.setEditable(false);
        emp.setBackground(Background.EMPTY);
        //emp.setBackground(null);
        emp.setLayoutX(180);
        emp.setLayoutY(245);
        emp.setMaxHeight(32);
        emp.setMaxWidth(215);

        TextField points = new TextField();
        points.setText(visit.points);
        points.setBackground(null);
        points.setLayoutX(180);
        points.setLayoutY(290);
        points.setMaxHeight(32);
        points.setMaxWidth(215);

        String[] mas_car = {"Внедорожник","Легковая","Грузовая","Мотоцикл"};
        ObservableList<String> car = FXCollections.observableArrayList(mas_car);
        ComboBox<String> comboBox4 = new ComboBox<String>(car);
        comboBox4.setMaxWidth(215);
        comboBox4.setValue(visit.type);
        comboBox4.setMinWidth(215);
        comboBox4.setBackground(null);
        comboBox4.setLayoutX(180);
        comboBox4.setLayoutY(330);

        Button save = new Button();
        save.setLayoutX(33);
        save.setLayoutY(363);
        save.setBackground(null);
        save.setPrefSize(150,32);

        Button del = new Button();
        del.setBackground(null);
        del.setLayoutX(220);
        del.setLayoutY(363);
        del.setPrefSize(150,32);
        root_add.getChildren().addAll(del,save);

        String finalId_visit = id;
        save.setOnAction(x ->{
            String t1,t2,t3,t4,t5,t6,t7 = "",t8;
            t1 = name.getText();
            t2 = comboBox2.getSelectionModel().getSelectedItem();
            t3 = comboBox3.getSelectionModel().getSelectedItem();
            t4 = DATA.getText();
            t5 = emp.getText();
            t6 = points.getText();
            t8 = comboBox4.getSelectionModel().getSelectedItem();
            {
                try {
                    t7 = Postgre.getPrice(t3);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Postgre.UpdateVisit(finalId_visit, visit.client_name, t2, t3, t4, t5, t7, t6, t8);
                    Pane p = Visit.getPane(flag);
                    ScrollFront.scrollPane.setContent(p);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                newWindow.close();
            }
        });

        del.setOnAction(va ->{
            try {
                Postgre.delVisit(id);
                Pane p = Visit.getPane(flag);
                ScrollFront.scrollPane.setContent(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            newWindow.close();
        });
        root_add.getChildren().addAll(name,emp,comboBox2,comboBox3, comboBox4);
        root_add.getChildren().addAll(DATA, points);
        newWindow.setTitle("Редактирование посещения");
        newWindow.setScene(scene_add);
        newWindow.show();
    }
    public static boolean chechPos(String date, String rate) {
        boolean isData=true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            System.out.println("date error");
            isData = false;
            return false;
        }
        boolean isRate = false;
        char[] r = rate.toCharArray();
        if (rate.length() == 1 && r[0] >'0' && r[0] <= '5')
            isRate = true;
        return isData & isRate;
    }

}
