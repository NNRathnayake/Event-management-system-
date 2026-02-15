/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public static boolean insertUser(String username, String password, String role) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) return false;

            String sql = "INSERT INTO users(username, password, role) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);

            pst.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}