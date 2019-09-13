package ru.solon4ak.jm_crudapp.dao;

import java.sql.SQLException;
import java.util.List;
import ru.solon4ak.jm_crudapp.model.User;

/**
 *
 * @author solon4ak
 */
public interface UserDao {
    
    User get(long id) throws SQLException;
    
    List<User> getAll() throws SQLException;
    
    int add(User user) throws SQLException;
    
    int update(User user) throws SQLException;
    
    int delete(long id) throws SQLException;
}
