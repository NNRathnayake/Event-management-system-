/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;



import java.sql.Timestamp;

public class EventRegistration {

    private int registrationId;
    private String username;
    private String eventId;
    private Timestamp requestTime;
    private String status;

    public EventRegistration(int registrationId, String username, String eventId, Timestamp requestTime, String status) {
        this.registrationId = registrationId;
        this.username = username;
        this.eventId = eventId;
        this.requestTime = requestTime;
        this.status = status;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public String getUsername() {
        return username;
    }

    public String getEventId() {
        return eventId;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public String getStatus() {
        return status;
    }
}