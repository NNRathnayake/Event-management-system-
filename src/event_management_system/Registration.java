/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;

/**
 *
 * @author USER
 */
public class Registration {
    private int userId;
    private String eventId;
    private String status;
    private java.sql.Timestamp requestTime;

    public Registration(int userId, String eventId, String status, java.sql.Timestamp requestTime) {
        this.userId = userId;
        this.eventId = eventId;
        this.status = status;
        this.requestTime = requestTime;
    }

    // getters
    public int getUserId() { return userId; }
    public String getEventId() { return eventId; }
    public String getStatus() { return status; }
    public java.sql.Timestamp getRequestTime() { return requestTime; }
}
