/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;

/**
 *
 * @author USER
 */

public class Session {

    private static Session instance; // single instance

    private int userId;
    private String username;
    private String role;

    // Private constructor to prevent external instantiation
    private Session() {
    }

    // Get the single instance of Session
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Login method: store user info
    public void login(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // Logout method: clear session
    public void logout() {
        this.userId = 0;
        this.username = null;
        this.role = null;
    }

    // Getters for user info
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    // Optional: check if someone is logged in
    public boolean isLoggedIn() {
        return username != null && !username.isEmpty();
    }
}