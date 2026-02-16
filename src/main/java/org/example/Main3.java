package org.example;

import org.example.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main3 {
    public static void main(String[] args) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "update Person set fname=?, lname=? WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "Ajay rao");
            ps.setString(2, "Lingampalli");
            ps.setInt(3, 1);
            int rowsEffected = ps.executeUpdate();
            System.out.println("Rows updated: " + rowsEffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
