/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package event_management_system;
//=====mergesort algorythm
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JOptionPane;
public class register extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(register.class.getName());
private java.util.Set<String> joinedEvents = new java.util.LinkedHashSet<>();
 private java.util.List<Event> eventList = new java.util.ArrayList<>();

/**
     * Creates new form register
     */
    public register() {
        initComponents();
        loadEvents(); 
    }

     //LOAD EVENTS 
    private void loadEvents() {

        JPanel eventsPanel = new JPanel();
        eventsPanel.setLayout(new javax.swing.BoxLayout(eventsPanel,
                javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(eventsPanel);

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM events");

            eventList.clear();
            boolean anyEvents = false;

            // STORE EVENTS
            while (rs.next()) {

                anyEvents = true;

                String id = rs.getString("event_id");
                String name = rs.getString("event_name");
                String place = rs.getString("place");
                int maxPeople = rs.getInt("max_people");
                String priority = rs.getString("priority");

                if (id == null || id.isEmpty()) continue;

                Event ev = new Event(name, place, maxPeople, priority);
                ev.setId(id);

                eventList.add(ev);
            }

            // ⭐ SORT USING MERGE SORT
            if (!eventList.isEmpty())
                mergeSort(eventList, 0, eventList.size() - 1);

            // DISPLAY SORTED EVENTS
            for (Event ev : eventList) {

                JPanel card = new JPanel();
                card.setBorder(javax.swing.BorderFactory
                        .createLineBorder(Color.GRAY, 2));
                card.setBackground(new Color(245,245,250));
                card.setMaximumSize(new java.awt.Dimension(600,80));
                card.setLayout(new java.awt.FlowLayout(
                        java.awt.FlowLayout.LEFT,10,5));

                card.add(new JLabel("📌 " + ev.getName()));
                card.add(new JLabel("ID: " + ev.getId()));
                card.add(new JLabel("Place: " + ev.getPlace()));
                card.add(new JLabel("Max: " + ev.getMaxPeople()));
                card.add(new JLabel("Priority: " + ev.getPriority()));

                javax.swing.JButton joinBtn =
                        new javax.swing.JButton("Join");

                if (isAlreadyRequested(ev.getId())) {
                    joinBtn.setText("Requested");
                    joinBtn.setEnabled(false);
                }

                joinBtn.addActionListener(e -> {
                    boolean success =
                            sendJoinRequest(ev.getId());
                    if(success){
                        joinBtn.setText("Requested");
                        joinBtn.setEnabled(false);
                        JOptionPane.showMessageDialog(
                                register.this,
                                "Request sent!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                card.add(joinBtn);
                eventsPanel.add(card);
            }

            if (!anyEvents)
                JOptionPane.showMessageDialog(this,
                        "No events found");

        } catch (Exception e) {
            e.printStackTrace();
        }

        eventsPanel.revalidate();
        eventsPanel.repaint();
    }


    // chek if alr requested
   private boolean isAlreadyRequested(String eventId) {
    try (Connection conn = DBConnection.getConnection();
         java.sql.PreparedStatement ps = conn.prepareStatement(
                 "SELECT COUNT(*) FROM event_registrations WHERE user_id=? AND event_id=?")) {

        int userId = Session.getInstance().getUserId(); 
        ps.setInt(1, userId);
        ps.setString(2, eventId);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(register.this, "Error checking existing requests:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}
   
   
   private int priorityValue(String p){
        if(p.equalsIgnoreCase("High")) return 3;
        if(p.equalsIgnoreCase("Medium")) return 2;
        return 1;
    }

    private void mergeSort(java.util.List<Event> list,
                           int left,
                           int right){

        if(left < right){

            int mid = (left + right) / 2;

            mergeSort(list,left,mid);
            mergeSort(list,mid+1,right);

            merge(list,left,mid,right);
        }
    }

    private void merge(java.util.List<Event> list,
                       int left,
                       int mid,
                       int right){

        java.util.List<Event> temp =
                new java.util.ArrayList<>();

        int i=left;
        int j=mid+1;

        while(i<=mid && j<=right){

            int p1 = priorityValue(
                    list.get(i).getPriority());
            int p2 = priorityValue(
                    list.get(j).getPriority());

            if(p1 >= p2){
                temp.add(list.get(i));
                i++;
            }else{
                temp.add(list.get(j));
                j++;
            }
        }

        while(i<=mid) temp.add(list.get(i++));
        while(j<=right) temp.add(list.get(j++));

        for(int k=0;k<temp.size();k++)
            list.set(left+k,temp.get(k));
    }

   
   
   
private boolean sendJoinRequest(String eventId) {
    try (Connection conn = DBConnection.getConnection();
         java.sql.PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO event_registrations (user_id, event_id, status) VALUES (?, ?, 'Requested')")) {

       int userId = Session.getInstance().getUserId();
        ps.setInt(1, userId);
        ps.setString(2, eventId);

       
        conn.setAutoCommit(true);

        System.out.println("Inserting registration: user_id=" + userId + ", event_id=" + eventId);
        int rows = ps.executeUpdate();

        if (rows > 0) {
          
            System.out.println("Insert successful!");
            return true;
        } else {
            System.out.println("No rows inserted.");
            return false;
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    
    
    
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("Event");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Management System");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jButton1.setBorder(null);
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

home h1 = new home();
h1.setVisible(true);
this.setVisible(false);


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new register().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
