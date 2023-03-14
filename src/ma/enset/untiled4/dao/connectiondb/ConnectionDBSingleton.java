package ma.enset.untiled4.dao.connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBSingleton {
    private static Connection connection;
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/db_stock";
    static final String USER = "root";
    static final String PASS = "";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if(connection==null || connection.isClosed())
                new ConnectionDBSingleton();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }}
