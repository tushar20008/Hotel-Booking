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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aditya
 */
public class ManagerDBUtils {
     
    public static List<Account> queryGetHotels(Connection conn) throws SQLException {
        String sql = "Select * from users where role='manager'";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Account> list = new ArrayList<Account>();
        while (rs.next()) {
            String code = rs.getString("id");
            String name = rs.getString("username");
            String password = rs.getString("password");
            Account manager = new Account(name, password);
            manager.setCode(code);
            list.add(manager);
        }
        return list;
    }
 
    public static Account findHotel(Connection conn, String code) throws SQLException {
        String sql = "Select * from users a where a.id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            
            Account manager = new Account(username, password);
            return manager;
        }
        return null;
    }
 
    public static void updateHotel(Connection conn, Account manager) throws SQLException {
        String sql = "Update users set username =?, password=? where id=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, manager.getUserName());
        pstm.setString(2, manager.getPassword());
        pstm.setString(3, manager.getCode());
        pstm.executeUpdate();
    }
 
    public static void insertHotel(Connection conn, Account manager) throws SQLException {
        String sql = "Insert into Product(username, password, role) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, manager.getUserName());
        pstm.setString(2, manager.getPassword());
        pstm.setString(3, manager.getRole());
 
        pstm.executeUpdate();
    }
 
    public static void deleteHotel(Connection conn, String code) throws SQLException {
        String sql = "Delete From users where Code= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, code);
 
        pstm.executeUpdate();
    }
}
