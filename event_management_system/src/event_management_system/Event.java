/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;


 
 

public class Event {
    private String id; // DB primary key
    private String name;
    private String place;
    private int maxPeople;
    private String priority;

    public Event(String name, String place, int maxPeople, String priority) {
        this.name = name;
        this.place = place;
        this.maxPeople = maxPeople;
        this.priority = priority;
    }

    public void setId(String id) { this.id = id; }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPlace() { return place; }
    public int getMaxPeople() { return maxPeople; }
    public String getPriority() { return priority; }

    public void setName(String name) { this.name = name; }
    public void setPlace(String place) { this.place = place; }
    public void setMaxPeople(int maxPeople) { this.maxPeople = maxPeople; }
    public void setPriority(String priority) { this.priority = priority; }
}