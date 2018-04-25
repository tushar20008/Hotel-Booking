/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.utils;

import hotel.beans.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aditya
 */
public class CommonUtils {
    public static Account findUser(Connection conn, //
            String userName, String password) throws SQLException {
 
        String sql = "Select * from users a where a.username = ? and a.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String role = rs.getString("role");
            Account user = new Account(userName, password, role);
            return user;
        }
        return null;
    }
 
    public static Account findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.username, a.password from users a "//
                + " where a.username = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("Password");
            String role = rs.getString("role");
            Account user = new Account(userName, password, role);
            return user;
        }
        return null;
    }
}
