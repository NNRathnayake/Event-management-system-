/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ActionLogger {

    // userId = the current user performing the action
    // actionType = "ADD_EVENT", "DELETE_EVENT", "JOIN_REQUEST", etc.
    // eventId = can be null if not related to an event
 public static void logAction(int userId, String actionType, String eventId, String status) {

        String sql = "INSERT INTO action_log (user_id, action_type, event_id, status) "
                   + "VALUES (?, ?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, userId);
            stmt.setString(2, actionType);
            stmt.setString(3, eventId);   // can be null
            stmt.setString(4, status);    // ACTIVE / UNDONE / REDONE

            stmt.executeUpdate();

            System.out.println("LOGGED -> User:"
                    + userId
                    + " Action:" + actionType
                    + " Event:" + eventId
                    + " Status:" + status);

        } catch (Exception e) {
            System.out.println("Logging failed!");
            e.printStackTrace();
        }
    }
}