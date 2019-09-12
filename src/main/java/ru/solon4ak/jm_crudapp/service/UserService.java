package ru.solon4ak.jm_crudapp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.solon4ak.jm_crudapp.dao.UserDao;
import ru.solon4ak.jm_crudapp.dbService.DBException;
import ru.solon4ak.jm_crudapp.dbService.DBHelper;
import ru.solon4ak.jm_crudapp.model.User;

/**
 *
 * @author solon4ak
 */
public class UserService {

    private static UserService userService;

    private Connection connection;

    private UserService() {
        this.connection = DBHelper.getConnection();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        try {
            UserDao userDao = new UserDao(connection);
            users = userDao.getAll();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return users;
    }

    public User getUserById(long id) throws DBException {
        try {
            UserDao userDao = new UserDao(connection);
            return userDao.get(id);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    public int addUser(User user) throws DBException {
        int res = 0;
        try {
            connection.setAutoCommit(false);
            UserDao userDao = new UserDao(connection);
            userDao.createTable();
            res = userDao.add(user);
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
            throw new DBException(ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
        }
        return res;
    }
    
    public int updateUser(User user) throws DBException {
        int res = 0;
        try {
            connection.setAutoCommit(false);
            UserDao userDao = new UserDao(connection);
            userDao.createTable();
            res = userDao.update(user);
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
            throw new DBException(ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
        }
        return res;
    }

    public int deleteUser(long id) throws DBException {
        int res = 0;
        try {
            connection.setAutoCommit(false);
            UserDao userDao = new UserDao(connection);
            res = userDao.delete(id);
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
            throw new DBException(ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
        }
        return res;
    }

    public void ClearData() throws DBException {
        UserDao userDao = new UserDao(connection);
        try {
            userDao.clearAll();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

}
