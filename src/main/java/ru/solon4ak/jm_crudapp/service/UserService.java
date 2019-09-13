package ru.solon4ak.jm_crudapp.service;

import java.util.List;
import ru.solon4ak.jm_crudapp.util.DBException;
import ru.solon4ak.jm_crudapp.model.User;

/**
 *
 * @author solon4ak
 */
public interface UserService {
    
    List<User> getAllUsers() throws DBException;    
    User getUserById(long id) throws DBException;
    int addUser(User user) throws DBException;
    int updateUser(User user) throws DBException;
    int deleteUser(long id) throws DBException;
}
