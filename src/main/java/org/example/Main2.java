package org.example;

import org.example.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) throws SQLException {

        Connection con= DatabaseConnection.getConnection();
        String sql="Insert into Person(fname,lname) Values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"Ajay");
            ps.setString(2, "lingampalli");
            int rowsEffected=ps.executeUpdate();
            System.out.println("Rows inserted:"+rowsEffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


