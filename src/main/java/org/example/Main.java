package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydb";
        String username="root";
        String password="root";
        Connection con=null;
        try {
            con= DriverManager.getConnection(url,username,password);
            String sql="CREATE TABLE Person(id int PRIMARY KEY AUTO_INCREMENT,fname varchar(25),lname varchar(25))";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}