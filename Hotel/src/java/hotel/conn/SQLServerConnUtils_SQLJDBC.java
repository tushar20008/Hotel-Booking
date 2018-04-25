/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnUtils_SQLJDBC {
 
    // Connect to SQL Server.
    // (Using JDBC Driver: SQLJDBC)
    public static Connection getSQLServerConnection_SQLJDBC() //
            throws ClassNotFoundException, SQLException {
 
        String hostName = "w2ksa.cs.cityu.edu.hk";
        String database = "aiad002_db";
        String userName = "aiad002";
        String password = "aiad002";
 
        return getSQLServerConnection_SQLJDBC(hostName, database, userName, password);
    }
 
    // Connect to SQLServer, using SQLJDBC Library.
    private static Connection getSQLServerConnection_SQLJDBC(String hostName, //
            String database, String userName, String password)//
            throws ClassNotFoundException, SQLException {
 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" //
                 + ";databaseName=" + database;
 
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
 
}