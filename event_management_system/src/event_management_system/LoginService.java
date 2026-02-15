/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;

/**
 *
 * @author USER
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService {

    // Validate user login
    // Returns role if successful, null if failed
    public static String validate(String username, String password) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Connection failed!");
                return null;
            }

            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("role"); // login successful
            } else {
                return null; // login failed
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pst != null) pst.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // Get user ID based on username
    public static int getUserId(String username) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int userId = 0;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Connection failed!");
                return 0;
            }

            String sql = "SELECT user_id FROM users WHERE username = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);

            rs = pst.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("user_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pst != null) pst.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return userId;
    }
}