package org.example.dao;

import org.example.connection.DatabaseConnection;
import org.example.entity.Person;
import java.sql.*;
import java.util.*;

public class PersonDaoImpl implements PersonDao {

    public List<Person> findAll() {
        return executeQuery("SELECT * FROM person");
    }

    public Person findById(int id) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> findByFnameContaining(String n) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE fname LIKE ? OR lname LIKE ?")) {
            ps.setString(1, "%" + n + "%");
            ps.setString(2, "%" + n + "%");
            ResultSet rs = ps.executeQuery();
            List<Person> result = getList(rs);
            if (result.isEmpty()) {
                result.add(new Person(0, "Person not found", ""));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> findByName(String fname, String lname) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE fname LIKE ? AND lname LIKE ?")) {
            ps.setString(1, "%" + fname + "%");
            ps.setString(2, "%" + lname + "%");
            ResultSet rs = ps.executeQuery();
            List<Person> result = getList(rs);
            if (result.isEmpty()) {
                result.add(new Person(0, "Person not found", ""));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> findSortedByFname() {
        return executeQuery("SELECT * FROM person ORDER BY fname");
    }

    public void addPerson(String fname, String lname) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO person(fname,lname) VALUES(?,?)")) {
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM person WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Person> executeQuery(String sql) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Person> getList(ResultSet rs) throws SQLException {
        List<Person> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Person map(ResultSet rs) throws SQLException {
        return new Person(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"));
    }
}
