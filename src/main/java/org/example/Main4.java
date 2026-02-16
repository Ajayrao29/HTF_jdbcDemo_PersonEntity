package org.example;

import org.example.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main4 {
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT * FROM Person";
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
               int id=rs.getInt("id");
               String fname=rs.getString("fname");
               String lname=rs.getString("lname");
                System.out.println("Id:"+id+" "+ "fname:"+fname+" "+ "lname:"+lname);

                //int id1=rs.getInt(1);
                //String fname1=rs.getString(2);
                //String lanme1=rs.getString(3);
                //System.out.println("Id:"+id+" "+ "fname:"+fname+" "+ "lname:"+lname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
