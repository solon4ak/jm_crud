package ru.solon4ak.jm_crudapp.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author solon4ak
 */
public class DBHelper {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    private static Connection createConnection() {

        try {
            DriverManager.registerDriver(
                    (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            );

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://"). //db type
                    append("localhost:"). //host name
                    append("3306/"). //port
                    append("db_example?"). //db name
                    append("user=root&"). //login
                    append("password=root&"). //password
                    append("useLegacyDatetimeCode=false&").
                    append("serverTimezone=UTC");
            
            Connection c = DriverManager.getConnection(url.toString());
            return c;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
//        Connection con = null;
//        try {
//            Context initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
//            DataSource ds = (DataSource) envContext.lookup("jdbc/db_example");
//            con = ds.getConnection();
//        } catch (NamingException | SQLException ex) {
//            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return con;
    }
    
}
