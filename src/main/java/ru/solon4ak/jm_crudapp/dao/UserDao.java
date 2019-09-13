package ru.solon4ak.jm_crudapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ru.solon4ak.jm_crudapp.dbService.DBHelper;
import ru.solon4ak.jm_crudapp.model.User;

/**
 *
 * @author solon4ak
 */
public class UserDao {

    private final Connection connection;
    
    private static UserDao instance;

    private UserDao() {
        this.connection = DBHelper.getConnection();
    }
    
    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public User get(long id) throws SQLException {
        String query = "select * from users where id='" + id + "'";
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        result.next();
        return new User(
                result.getLong("id"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("email"),
                result.getString("address"),
                result.getString("phone_number"),
                result.getByte("age")
        );
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        User u;
        while (result.next()) {
            u = new User(
                    result.getLong("id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("email"),
                    result.getString("address"),
                    result.getString("phone_number"),
                    result.getByte("age")
            );
            users.add(u);
        }
        return users;
    }

    public int add(User user) throws SQLException {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("insert into users (first_name, last_name, email, address, phone_number, age) ");
        sb.append("values (?, ?, ?, ?, ?, ?)");
        try {
            createTable();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sb.toString());
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPhoneNumber());
            ps.setByte(6, user.getAge());
            result = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

        return result;
    }

    public int update(User user) throws SQLException {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("update users set first_name=?, last_name=?, email=?, ");
        sb.append("address=?, phone_number=?, age=? ");
        sb.append("where id=?");
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sb.toString());
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPhoneNumber());
            ps.setByte(6, user.getAge());
            ps.setLong(7, user.getId());
            result = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

        return result;
    }

    public int delete(long id) throws SQLException {
        int result = 0;
        String query = "delete from users where id=?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            result = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

        return result;
    }

    public void createTable() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists users ");
        sb.append("(id bigint auto_increment, ");
        sb.append("first_name varchar(256), ");
        sb.append("last_name varchar(256), ");
        sb.append("email varchar(256), ");
        sb.append("address varchar(512), ");
        sb.append("phone_number varchar(15), ");
        sb.append("age TINYINT UNSIGNED, ");
        sb.append("primary key (id))");

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sb.toString());
    }

    public void clearAll() throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate("drop table users");
    }
}
