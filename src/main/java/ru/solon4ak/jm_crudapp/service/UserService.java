package ru.solon4ak.jm_crudapp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ru.solon4ak.jm_crudapp.dao.UserDao;
import ru.solon4ak.jm_crudapp.dbService.DBException;
import ru.solon4ak.jm_crudapp.model.User;

/**
 *
 * @author solon4ak
 */
public class UserService {

    private static UserService userService;
    
    private UserDao userDao;

    private UserService() {
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
            userDao = UserDao.getInstance();
            users = userDao.getAll();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return users;
    }

    public User getUserById(long id) throws DBException {
        try {
            userDao = UserDao.getInstance();
            return userDao.get(id);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    public int addUser(User user) throws DBException {
        int result = 0;
        try {
            userDao = UserDao.getInstance();
            result = userDao.add(user);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return result;
    }
    
    public int updateUser(User user) throws DBException {
        int result = 0;
        try {            
            userDao = UserDao.getInstance();
            result = userDao.update(user);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return result;
    }

    public int deleteUser(long id) throws DBException {
        int result = 0;
        try {
            userDao = UserDao.getInstance();
            result = userDao.delete(id);
        } catch (SQLException ex) {            
            throw new DBException(ex);
        }
        return result;
    }

    public void ClearData() throws DBException {
        userDao = UserDao.getInstance();
        try {
            userDao.clearAll();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

}
