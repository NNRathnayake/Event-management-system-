/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author USER
 */
public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; 
    private static final String USER = "SYSTEM"; 
    private static final String PASS = "1234";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connected to Oracle DB!");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
