package ru.solon4ak.jm_crudapp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ru.solon4ak.jm_crudapp.dao.UserDao;
import ru.solon4ak.jm_crudapp.dao.UserDaoImpl;
import ru.solon4ak.jm_crudapp.util.DBException;
import ru.solon4ak.jm_crudapp.model.User;

/**
 *
 * @author solon4ak
 */
public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    private UserDao userDao;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        try {
            userDao = UserDaoImpl.getInstance();
            users = userDao.getAll();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return users;
    }

    @Override
    public User getUserById(long id) throws DBException {
        try {
            userDao = UserDaoImpl.getInstance();
            return userDao.get(id);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    @Override
    public int addUser(User user) throws DBException {
        int result = 0;
        try {
            userDao = UserDaoImpl.getInstance();
            if (user != null) {
                result = userDao.add(user);
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return result;
    }

    @Override
    public int updateUser(User user) throws DBException {
        int result = 0;
        try {
            userDao = UserDaoImpl.getInstance();
            if (user != null) {
                result = userDao.update(user);
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return result;
    }

    @Override
    public int deleteUser(long id) throws DBException {
        int result = 0;
        try {
            userDao = UserDaoImpl.getInstance();
            result = userDao.delete(id);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return result;
    }

}
