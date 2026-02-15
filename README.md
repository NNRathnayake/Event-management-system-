# Event Management System

A complete desktop Event Management System built using *Java Swing* and **PLSQL**, featuring event registration, attendee tracking, and dashboard stats.





1. Clone or download the project.
2. Open the project in **NetBeans** (or any Java IDE supporting Swing).
3. Configure `DBConnection.java` with your database credentials.
4. Run `AttendeeM.java` to launch the dashboard.
5. Use the GUI to manage events, registrations, and attendees.





👥 User Roles

 🔴 Admin
- Full control of events and users  
- Accept or delete registrations  
- View dashboard stats (total attendees, total events, average per event)  
 🟢 User
- Browse events  
- Register for events  
- Cancel registrations

- 

 Data Structures

Array - Store events in order
Dictionary - Quick lookup by ID
Set - Prevent duplicate registrations
Queue - Fair FIFO processing
Stack - Undo functionality
Heap - Priority-based handling
BST - Date-organized search





💡 Features

1. **Dashboard Stats** – Total attendees, total events, average attendees per event  
2. **Search Events** – Search registrations by Event ID  
3. **Accept/Delete Registrations** – One-click actions for admins  
4. **Dynamic Display** – GUI updates automatically when changes occur  
5. **Database Integration** – Fully connected with MySQL
6. use queue
7. manage events
8. accept requests
9. join with an event
10. see events based on priority
11. redo undo history
12. 
  
13. **Transactional Safety** – Optional auto-commit/off mode for multi-step operations  
Color(245, 245, 250)); // card background
card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // card border
