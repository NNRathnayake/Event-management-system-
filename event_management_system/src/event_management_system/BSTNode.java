/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;

/**
 *
 * @author USER
 */
class BSTNode {
    String eventName;
    String eventId;
    java.sql.Date date;
    BSTNode left, right;

    BSTNode(String eventId, String eventName, java.sql.Date date) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.date = date;
        left = right = null;
    }
}