package com.example.sotnya.DataBase;
import com.example.sotnya.Front;
import com.example.sotnya.PaneModel.*;

import java.io.FileNotFoundException;
import java.sql.*;

public class Postgre {
    static Connection data_connection;

    static Connection connection;
    static Statement data_statmt;

    static Statement statmt;
    static ResultSet data_resSet;
    public Postgre(String login, String pass) throws SQLException {
        data_connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Sotnya",login,pass);
        data_statmt = data_connection.createStatement();
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Sotnya","postgres","123");
        statmt = connection.createStatement();
    }

    public static String getRole() throws SQLException {
        data_resSet = data_statmt   .executeQuery("select rolname from pg_user\n" +
                "join pg_auth_members on (pg_user.usesysid=pg_auth_members.member)\n" +
                "join pg_roles on (pg_roles.oid=pg_auth_members.roleid)\n" +
                "where usename = current_user;");
        String role="";
        while (data_resSet.next()){
            role = data_resSet.getString("rolname");
        }
        return role;
    }
    public static Structure[] getAllStructure() throws SQLException {
        String r = getLastStructure();
        int kol = Integer.parseInt(r);
        Structure[] mas = new Structure[kol];
        data_resSet = data_statmt.executeQuery("SELECT structure_id, " +
                "structure_name, address, specialty_name, post_index, tel_number, " +
                "num_employees \n" + "FROM structure ORDER by structure_id asc;");
        int i=0;
        while (data_resSet.next()) {
            String t1 = data_resSet.getString("structure_id");
            String t2 = data_resSet.getString("structure_name");
            String t3 = data_resSet.getString("address");
            String t4 = data_resSet.getString("specialty_name");
            String t5 = data_resSet.getString("post_index");
            String t6 = data_resSet.getString("tel_number");
            String t7 = data_resSet.getString("num_employees");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas[i] = new Structure(t1,t2,t3,t4,t5,t6,t7);
            i++;
        }
        return mas;
    }
    public static Structure getStructure_byID(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        data_resSet = data_statmt.executeQuery("SELECT structure_id, structure_name, address, specialty_name, post_index, tel_number, " +
                "num_employees \n" +
                "FROM structure WHERE structure_id = "+ id + ";");
        Structure mas = null;
        while (data_resSet.next()) {
            String t1 = data_resSet.getString("structure_id");
            String t2 = data_resSet.getString("structure_name");
            String t3 = data_resSet.getString("address");
            String t4 = data_resSet.getString("specialty_name");
            String t5 = data_resSet.getString("post_index");
            String t6 = data_resSet.getString("tel_number");
            String t7 = data_resSet.getString("num_employees");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas = new Structure(t1,t2,t3,t4,t5,t6,t7);
        }
        return mas;
        //  System.out.println("-");
    }
    public static void UpdateStructure(String id, String t1, String t2, String t3, String t4, String t5, String t6) throws SQLException {
        //System.out.println(t1+"|"+t2+"|"+ t3+"|" + t4);
        data_statmt.execute("UPDATE structure " +
                "set structure_name = '"  + t1 + "'," +
                " address = '"+ t2 + "'," +
                " specialty_name = '" + t3 + "'," +
                " post_index = '" + t4 + "'," +
                " tel_number = '" + t5 + "'," +
                " num_employees = " + t6 +
                " where structure_id = " + id+";");
    }

    public static String getLastStructure() throws SQLException {
        String count;
        data_resSet = statmt.executeQuery("SELECT count(structure_id) as w FROM structure;");
        data_resSet.next();
        count = data_resSet.getString("w");
        return count;
    }
    public static Employee[] getAllEmployee() throws SQLException, ClassNotFoundException, FileNotFoundException {
        String id = Postgre.getLastEmployee();
        int kol = Integer.parseInt(id);
        Employee[] mas = new Employee[kol];
        data_resSet = data_statmt.executeQuery("SELECT * \n" +
                "FROM employee ORDER BY employee_id asc;");
        int i=0;
        while (data_resSet.next()) {
            String t1 = data_resSet.getString("employee_id");
            String t2 = data_resSet.getString("employee_name");
            String t3 = data_resSet.getString("employee_post");
            String t4 = data_resSet.getString("employee_exp");
            String t5 = data_resSet.getString("employee_sal");

            String t6 = data_resSet.getString("employee_inf");
            String t7 = data_resSet.getString("employee_age");
            String t8 = data_resSet.getString("employee_score");
            String t9 = data_resSet.getString("employee_num");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4 + " | " + t5 + " | " + t6 + " | " + t7 + " | " + t8 + " | " + t9);
            mas[i] = new Employee(t1,t2,t3,t4,t5,t6,t7,t8,t9);
            i++;
        }
        System.out.println("-");
        return mas;
    }
    public static String[] getEmployee_name() throws SQLException, ClassNotFoundException, FileNotFoundException {
        String r = getLastEmployee();
        //System.out.println("kol: " + r);
        int kol = Integer.parseInt(r);
        String[] mas = new String[kol];
        data_resSet = statmt.executeQuery("SELECT employee_name FROM employee;");
        int i=0;
        while (data_resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = data_resSet.getString("employee_name");
            mas[i] = t1;
            i++;
        }
        System.out.println("-");
        return mas;
    }
    public static Employee[] getAllEmployeeWork() throws SQLException, ClassNotFoundException, FileNotFoundException {
        String id = Postgre.getLastEmployee();
        int kol = Integer.parseInt(id);
        Employee[] mas = new Employee[kol];
        data_resSet = data_statmt.executeQuery("SELECT * FROM employee left join work " +
                "using(employee_id) join structure using(structure_id);");
        int i=0;
        while (data_resSet.next()) {
            String t1 = data_resSet.getString("employee_id");
            String t2 = data_resSet.getString("employee_name");
            String t3 = data_resSet.getString("work_id");
            String t4 = data_resSet.getString("structure_name");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4 + " | " + t5 + " | " + t6 + " | " + t7 + " | " + t8 + " | " + t9);
            mas[i] = new Employee(t1,t2,t3,t4);
            i++;
        }
        System.out.println("-");
        return mas;
    }
    public static void delWork(String workID) throws SQLException {
        statmt.execute("DELETE FROM public.work\n" +
                "\tWHERE work_id ='"+workID+"';");
    }

    public static String getLastEmployee() throws SQLException {
        String id;
        data_resSet = data_statmt.executeQuery("SELECT count(employee_id) FROM employee;");
        data_resSet.next();
        id = data_resSet.getString("count");
        return id;
    }

    public static Employee getEmployee_byID(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        data_resSet = data_statmt.executeQuery("SELECT * \n" +
                "FROM employee WHERE employee_id = "+ id + ";");
        Employee mas = null;
        while (data_resSet.next()) {
            String t1 = data_resSet.getString("employee_id");
            String t2 = data_resSet.getString("employee_name");
            String t3 = data_resSet.getString("employee_post");
            String t4 = data_resSet.getString("employee_exp");
            String t5 = data_resSet.getString("employee_sal");
            String t6 = data_resSet.getString("employee_inf");
            String t7 = data_resSet.getString("employee_age");
            String t8 = data_resSet.getString("employee_score");
            String t9 = data_resSet.getString("employee_num");
            mas = new Employee(t1,t2,t3,t4,t5,t6,t7,t8,t9);
        }
        return mas;
    }
    public static void UpdateEmployee(String id, String t1, String t2, String t3, String t4,
                                      String t5, String t6, String t7, String t8) throws SQLException {
        data_statmt.execute("UPDATE employee " +
                "set employee_name = '"  + t1 + "'," +
                " employee_post = '"+ t2 + "'," +
                " employee_exp = " + t3 + "," +
                " employee_inf = '" + t5 + "'," +
                " employee_age = " + t6 + "," +
                " employee_num = " + t8 +
                " where employee_id = " + id+";");
    }

    public static Client[] getAllClient() throws SQLException, ClassNotFoundException, FileNotFoundException {
        String r = getLastClient();
        int kol = Integer.parseInt(r);
        Client[] mas = new Client[kol];
        data_resSet = data_statmt.executeQuery("SELECT * \n" +
                "FROM client ORDER by client_id asc;");
        int i=0;
        while (data_resSet.next()) {
            String t1 = data_resSet.getString("client_id");
            String t2 = data_resSet.getString("client_name");
            String t3 = data_resSet.getString("client_status");
            String t4 = data_resSet.getString("client_bonus");
            System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas[i] = new Client(t1,t2,t3,t4);
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public static String getLastClient() throws SQLException {
        String id;
        data_resSet = data_statmt.executeQuery("SELECT count(client_id) FROM client;");
        data_resSet.next();
        id = data_resSet.getString("count");
        return id;
    }
    public static Client getClient_byID(String id) throws SQLException {
        data_resSet = data_statmt.executeQuery("SELECT * FROM client WHERE client_id = '" + id + "';");
        Client mas = null;
        while(data_resSet.next()) {
            String name = data_resSet.getString("client_name");
            String status = data_resSet.getString("client_status");
            String bonus = data_resSet.getString("client_bonus");
            mas = new Client(id,name,status,bonus);
        }
        return mas;
    }

    public static void UpdateClient(String t1, String t2, String t3, String t4) throws SQLException {
        System.out.println(t1+"|"+t2+"|"+ t3+"|" + t4);
        statmt.execute("UPDATE client " +
                "set client_id = "  + t1 + "," +
                " client_name = '"+ t2 + "'," +
                " client_status = '" + t3 + "'," +
                " client_bonus = " + t4 +
                " where client_id = " + t1+";");
    }

    public static void delClient(String t1) throws SQLException {
        statmt.execute("DELETE FROM visit "+
                " where client_id = " + t1+";");
        statmt.execute("DELETE FROM client "+
                " where client_id = " + t1+";");
    }

    public  static void addClient(String name) throws SQLException {
        data_statmt.execute("INSERT INTO public.client(\n" +
                "\tclient_name)\n" +
                "\tVALUES ('" + name + "');");
    }

    public  static void addStructure(String name, String address, String type, String post, String num, String kol_empl) throws SQLException {
        data_statmt.execute("INSERT INTO public.structure(\n" +
                "\tstructure_name, address, post_index, tel_number, num_employees, specialty_name)\n" +
                "\tVALUES ('" + name + "', '" + address + "', '" + post + "', '" + num + "', '" + kol_empl + "', '" + type+"' );");
    }
    public  static void addEmployee(String name, String position, String exp, String sal,
                                    String inf,String age,String score, String num, String login, String passSalt, String pass) throws SQLException, FileNotFoundException, ClassNotFoundException {
        statmt.execute("INSERT INTO public.employee(\n" +
                "\temployee_name, employee_exp, employee_sal, employee_inf, employee_age, employee_score, employee_num, employee_post)\n" +
                "\tVALUES ('" + name +"', '" + exp + "', '" + sal + "', '" + inf + "', " + age + ", '" + score + "', " + num + ", '" + position +"');");
        String employee_id = getEmployee_byName(name);
        statmt.execute("INSERT INTO public.\"user\"(\n" +
                "\temployee_id, login, password)\n" +
                "\tVALUES ('" + employee_id + "', '" + login + "', '" + passSalt + "');");
        statmt.execute("CREATE ROLE \""+ login  +"\""+
                "\tLOGIN\n" +
                "\tNOSUPERUSER\n" +
                "\tNOCREATEDB\n" +
                "\tNOCREATEROLE\n" +
                "\tINHERIT\n" +
                "\tNOREPLICATION\n" +
                "\tCONNECTION LIMIT -1\n" +
                "\tPASSWORD '" + pass + "';");
        String lvl="Staff";
        if(position.equals("Администратор"))
            lvl = "Administrator";
        else if(position.equals("Аналитик"))
            lvl = "Analyst";
        else  if(position.equals("Управляющий"))
            lvl = "Local_manager";
        else if(position.equals("Менеджер"))
            lvl = "Manager";
        statmt.execute("GRANT \""+lvl+"\" TO \""+ login+"\" WITH ADMIN OPTION;");
    }

    public static void UpdateUser(String id, String login, String pass, String passSalt, String position, boolean fl, String oldRole) throws SQLException {
        String lvl="Staff";
        if(position.equals("Администратор"))
            lvl = "Administrator";
        else if(position.equals("Аналитик"))
            lvl = "Analyst";
        else  if(position.equals("Управляющий"))
            lvl = "Local_manager";
        else if(position.equals("Менеджер"))
            lvl = "Manager";
        String oldLogin = getEmployeeLogin_byID(id);
        if(!oldLogin.equals(login))
            statmt.execute("ALTER ROLE \""+ oldLogin +"\" RENAME TO \""+login+"\";");
        if(!oldRole.equals(lvl)) {
            statmt.execute("REVOKE \"" + oldRole + "\" FROM \"" + login + "\";");
            statmt.execute("GRANT \"" + lvl + "\" TO \"" + login + "\" WITH ADMIN OPTION;");
        }
        if(fl){
            statmt.execute("UPDATE \"user\" " +
                    "set login = '"  + login + "'" +
                    " where employee_id = " + id+";");
        }
        else {
            statmt.execute("UPDATE \"user\" " +
                    "set login = '" + login + "'," +
                    " password = '" + passSalt + "'" +
                    " where employee_id = " + id + ";");
            statmt.execute("ALTER ROLE \"" + login + "\"\n" +
                    "\tPASSWORD '" + pass + "';");
        }
    }

    public static String getEmployeeLogin_byID(String id) throws SQLException {
        String login ="";
        data_resSet = statmt.executeQuery("Select login from public.\"user\" where employee_id ='"+ id+"';");
        while (data_resSet.next()){
            login = data_resSet.getString("login");
        }
        return login;
    }
    public static String getEmployeeRole_byID(String id) throws SQLException {
        String role ="";
        data_resSet = statmt.executeQuery("Select employee_post from public.employee where employee_id ='"+ id+"';");
        while (data_resSet.next()){
            role = data_resSet.getString("employee_post");
        }
        System.out.println("ollllld"+ role);
        if(role.equals("Аналитик"))
            role = "Analyst";
        else if(role.equals("Администратор"))
            role = "Administrator";
        else if(role.equals("Менеджер"))
            role = "Manager";
        else if(role.equals("Управляющий"))
            role = "Local_manager";
        else
            role = "Staff";
        return role;
    }

    public static void delEmployee(String t1) throws SQLException, FileNotFoundException, ClassNotFoundException {
        statmt.execute("DELETE FROM visit "+
                " where employee_id = " + t1+";");
        String t2 = getEmployeeLogin_byID(t1);
        statmt.execute("DELETE FROM \"user\" "+
                " where employee_id = " + t1+";");
        statmt.execute("DELETE FROM work "+
                " where employee_id = " + t1+";");

        statmt.execute("DELETE FROM employee "+
                " where employee_id = " + t1+";");
        statmt.execute("DROP ROLE \"" + t2 + "\";");
    }


    public static String[] getStructure_name() throws SQLException, ClassNotFoundException, FileNotFoundException {
        String r = getLastStructure();
        int kol = Integer.parseInt(r);
        String[] mas = new String[kol];
        data_resSet = statmt.executeQuery("SELECT structure_name FROM structure;");
        int i=0;
        while (data_resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = data_resSet.getString("structure_name");
            mas[i] = t1;
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public static String[] getService_name(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastService();
        int kol = Integer.parseInt(r);
        String[] mas = new String[kol];
        data_resSet = statmt.executeQuery("SELECT * FROM service join manage using(service_id) " +
                "join public.\"structure\" using (structure_id) where structure_name ='"+ name+"';");
        int i=0;
        while (data_resSet.next()) {
            String t2 = data_resSet.getString("service_name");
            System.out.println("t2:"+t2);
            mas[i] = t2;
            i++;
        }
        return mas;
        //  System.out.println("-");
    }
    public static String getLastService() throws SQLException {
        String id ="";
        data_resSet = statmt.executeQuery("SELECT count(service_id) FROM service;");
        while(data_resSet.next())
            id = data_resSet.getString("count");
        return id;
    }

    public static String getMyName() throws SQLException, ClassNotFoundException, FileNotFoundException {
        data_resSet = statmt.executeQuery("select * from \"user\"\n" +
                "join employee using(employee_id) where login ='" + Front.login+"';");
        String t ="";
        while (data_resSet.next()) {
            t = data_resSet.getString("employee_name");
        }
        return t;
    }

    public static String getPrice(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        data_resSet = statmt.executeQuery("SELECT service_price FROM service WHERE service_name = '"+ name + "';");
        String t ="";
        while (data_resSet.next()) {
            t = data_resSet.getString("service_price");
        }
        return t;
        //  System.out.println("-");
    }

    public  static void addVisit(String client_id, String str, String ser, String date,
                                 String summa, String points) throws SQLException, FileNotFoundException, ClassNotFoundException {
        str = getStructure_byNAME(str);
        ser = getService_byNAME(ser);
        String emp = getEmployee_byLogin(Front.login);
        System.out.println(str+"|"+ser+"|"+emp);
        System.out.println(points);
        statmt.execute("INSERT INTO public.visit(\n" +
                "\tclient_id, structure_id, service_id, data, employee_id, summa, points)\n" +
                "\tVALUES (" +  client_id + ", " + str + ", " + ser + ", '" + date + "', " + emp + ", " +
                summa + ", " + points +");");
    }

    public static String getStructure_byNAME(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        data_resSet = statmt.executeQuery("SELECT structure_id FROM structure WHERE structure_name = '"+ name+"';");
        while (data_resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = data_resSet.getString("structure_id");
            t = t1;
        }
        System.out.println("-");
        return t;
    }
    public static String getService_byNAME(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        data_resSet = statmt.executeQuery("SELECT service_id FROM service WHERE service_name = '"+ name+"';");
        while (data_resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = data_resSet.getString("service_id");
            t = t1;
        }
        System.out.println("-");
        return t;
    }

    public static String getEmployee_byLogin(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        data_resSet = statmt.executeQuery("SELECT employee_id FROM \"user\" WHERE login = '"+ name+"';");
        while (data_resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = data_resSet.getString("employee_id");
            t = t1;
        }
        System.out.println("-");
        return t;
    }

    public static String getEmployee_byName(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        data_resSet = statmt.executeQuery("SELECT employee_id FROM employee WHERE employee_name = '"+ name+"';");
        while (data_resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = data_resSet.getString("employee_id");
            t = t1;
        }
        System.out.println("-");
        return t;
    }

    public static Client[] findClient_byNAME(String Findname) throws SQLException {
        String k = getLastClient();
        int kol = Integer.parseInt(k);
        Client[] mas = new Client[kol];
        data_resSet = data_statmt.executeQuery("SELECT * FROM client \n" +
                "WHERE position('" + Findname + "' in client_name)>0;");
        int i = 0;
        while(data_resSet.next()) {
            //System.out.println("i/"+i);
            String id = data_resSet.getString("client_id");
            String name = data_resSet.getString("client_name");
            String status = data_resSet.getString("client_status");
            String bonus = data_resSet.getString("client_bonus");
            mas[i] = new Client(id,name,status,bonus);
            i++;
        }
        return mas;
    }

    public static Visit[] getAllVisit_Staff() throws SQLException, ClassNotFoundException, FileNotFoundException {
        String r = getLastVisit();
        int kol = Integer.parseInt(r);
        Visit[] mas = new Visit[kol];
        data_resSet = statmt.executeQuery("SELECT visit_id, client_name, structure_name, service_name, \"data\", employee_name, summa, points FROM visit\n" +
                "JOIN client using(client_id)\n" +
                "JOIN structure using(structure_id)\n" +
                "JOIN service using(service_id)\n" +
                "JOIN employee using(employee_id)" +
                " ORDER BY visit_id asc;");
        int i=0;
        while (data_resSet.next()) {
            System.out.println("i:"+i);
            String t1 = data_resSet.getString("visit_id");
            String t2 = data_resSet.getString("client_name");
            String t3 = data_resSet.getString("structure_name");
            String t4 = data_resSet.getString("service_name");
            String t5 = data_resSet.getString("data");
            String t6 = data_resSet.getString("employee_name");
            String t7 = data_resSet.getString("summa");
            String t8 = data_resSet.getString("points");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas[i] = new Visit(t1,t2,t3,t4,t5,t6,t7,t8);
            i++;
        }
        System.out.println("-");
        return mas;
    }
    public static String getLastVisit() throws SQLException {
        String id;
        data_resSet = statmt.executeQuery("SELECT count(visit_id) FROM visit;");
        data_resSet.next();
        id = data_resSet.getString("count");
        //System.out.println("maxID_client: " + id);
        return id;
    }

    public static Visit getVisit_byID(String id) throws SQLException {
        data_resSet = statmt.executeQuery("SELECT * FROM visit\n" +
                "JOIN client using(client_id)\n" +
                "JOIN structure using(structure_id)\n" +
                "JOIN service using(service_id)\n" +
                "JOIN employee using(employee_id)" +
                " WHERE visit_id =" + id + ";");
        int i=0;
        Visit mas = null;
        while(data_resSet.next()) {
            String clientID = data_resSet.getString("client_id");
            String structureName = data_resSet.getString("structure_name");
            String data = data_resSet.getString("data");
            String serviceName = data_resSet.getString("service_name");
            String employeeName = data_resSet.getString("employee_name");
            String summa = data_resSet.getString("summa");
            String points = data_resSet.getString("points");
            mas = new Visit(id,clientID,structureName,serviceName,data,employeeName,summa,points);
        }
        return mas;
    }

    public static String getClientName_byID(String id) throws SQLException {
        String name="";
        data_resSet = statmt.executeQuery("SElect * from client where client_id ='"+id+"';");
        while (data_resSet.next()){
            name = data_resSet.getString("client_name");
        }
        return name;
    }

    public static void UpdateVisit(String visit_id, String client_id, String str, String ser, String date,
                                   String emp,String summa, String points) throws SQLException, FileNotFoundException, ClassNotFoundException {
        str = Postgre.getStructure_byNAME(str);
        ser = Postgre.getService_byNAME(ser);
        // System.out.println(emp);
        emp = Postgre.getEmployee_byName(emp);
        //System.out.println(str+"|"+ ser+"|"+"|"+date+"|"+emp);
        statmt.execute("UPDATE visit " +
                "set client_id = "  + client_id + "," +
                " structure_id = "+ str + "," +
                " service_id = " + ser + "," +
                " data = '" + date + "'," +
                " employee_id = " + emp + "," +
                " summa = " + summa + "," +
                " points = " + points +
                " where visit_id = " + visit_id+";");
    }
    public static void delVisit(String t1) throws SQLException {
        statmt.execute("DELETE FROM visit "+
                " where visit_id = " + t1+";");
    }

    public static void task1() throws SQLException {
        statmt.execute("COPY (SELECT sum(summa) as \"Доход от услуги\", service_name as \"Название услуги\"\n" +
                "\t  FROM visit\n" +
                "\t  JOIN service using(service_id)\n" +
                "\t  GROUP BY \"Название услуги\"\n" +
                "\t  ORDER BY \"Доход от услуги\" desc\n" +
                "\t  LIMIT 3\n" +
                "\t  )\n" +
                "\t TO 'D:\\postgreSQL\\max_service.csv' CSV HEADER\n" +
                "\t DELIMITER '|' ENCODING 'WIN1251';");
    }

    public static void task2() throws SQLException {
        statmt.execute("COPY (SELECT sum(summa) as \"Доход от услуги\", service_name as \"Название услуги\"\n" +
                "\t  FROM visit\n" +
                "\t  JOIN service using(service_id)\n" +
                "\t  GROUP BY \"Название услуги\"\n" +
                "\t  ORDER BY \"Доход от услуги\" asc\n" +
                "\t  LIMIT 3\n" +
                "\t  )\n" +
                "\t TO 'D:\\postgreSQL\\min_service.csv' CSV HEADER\n" +
                "\t DELIMITER '|' ENCODING 'WIN1251';");
    }
    public static void task5() throws SQLException {
        statmt.execute("COPY (SELECT employee_name, employee_score\n" +
                "\t  FROM employee\n" +
                "\t  ORDER BY employee_score DESC\n" +
                "\t  LIMIT 3\n" +
                "\t )\n" +
                "\t TO 'D:\\postgreSQL\\avg_employee.csv' CSV HEADER\n" +
                "\t DELIMITER '|' ENCODING 'WIN1251';");
    }
    public static void task3() throws SQLException {
        statmt.execute("COPY (SELECT sum(summa) as \"Общий доход\"\n" +
                "    FROM visit\n" +
                "\t  )\n" +
                "    TO 'D:\\postgreSQL\\all_capital.csv' CSV HEADER\n" +
                "    DELIMITER '|' ENCODING 'WIN1251';");
    }

    public static Service[] getService() throws SQLException, ClassNotFoundException, FileNotFoundException {
        String r = getLastService();
        int kol = Integer.parseInt(r);
        Service[] mas = new Service[kol];
        data_resSet = data_statmt.executeQuery("SELECT * FROM service ORDER by service_id asc;");
        int i=0;
        while (data_resSet.next()) {
            String t1 = data_resSet.getString("service_id");
            String t2 = data_resSet.getString("service_name");
            String t3 = data_resSet.getString("service_price");
            mas[i] = new Service(t1,t2,t3);
            i++;
        }
        return mas;
    }


    public static void addWork(String w1, String w2) throws SQLException, FileNotFoundException, ClassNotFoundException {
        w2 = getStructure_byNAME(w2);
        w1 = getEmployee_byName(w1);
        statmt.execute("INSERT INTO public.work(\n" +
                "\tstructure_id, employee_id)\n" +
                "\tVALUES ('"+ w2+"', '"+w1+"');");
    }

}
