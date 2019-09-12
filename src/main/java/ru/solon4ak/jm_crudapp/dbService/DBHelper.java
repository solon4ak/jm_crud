package ru.solon4ak.jm_crudapp.dbService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author solon4ak
 */
public class DBHelper {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
            printConnectInfo();
        }
        return connection;
    }

    private static Connection createConnection() {

//        try {
//            DriverManager.registerDriver(
//                    (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
//            );
//
//            StringBuilder url = new StringBuilder();
//            url.
//                    append("jdbc:mysql://"). //db type
//                    append("localhost:"). //host name
//                    append("3306/"). //port
//                    append("db_example?"). //db name
//                    append("user=root&"). //login
//                    append("password=root&"). //password
//                    append("useLegacyDatetimeCode=false&").
//                    append("serverTimezone=UTC");
//            
//            Connection c = DriverManager.getConnection(url.toString());
//            return c;
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
//            e.printStackTrace();
//            throw new IllegalStateException();
//        }
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/db_example");
            con = ds.getConnection();
//            context = new InitialContext();
//            DataSource ds = (DataSource) context.lookup("jdbc/db_example");
//            con = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

//            Connection c = DriverManager.getConnection(url.toString());
        return con;
    }

    public static void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
