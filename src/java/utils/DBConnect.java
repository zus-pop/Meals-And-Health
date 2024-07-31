/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hoang
 */
public class DBConnect {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        Connection conn;
        String url = "jdbc:sqlserver://ZUS\\POP;databaseName=MEALS_AND_HEALTH";
        String username = "sa";
        String password = "12345";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        System.out.println(conn.getMetaData().getDriverName());
        System.out.println(conn.getMetaData().getDriverVersion());
    }
}
