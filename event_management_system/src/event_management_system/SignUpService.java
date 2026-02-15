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

public class SignUpService {

    // Insert a new user into the DB
    // Returns true if successful, false if failed
    public static boolean registerUser(String username, String password, String role) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            //: Connect to DB
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("DB Connection failed!");
                return false;
            }

            // Prepare SQL INSERT
            String sql = "INSERT INTO users(username, password, role) VALUES (?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);

            //  Execute INSERT
            int rowsInserted = pst.executeUpdate();

            return rowsInserted > 0; // return true if at least one row inserted

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            //  Close resources
            try { if (pst != null) pst.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}