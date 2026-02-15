/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;

/**
 *
 * @author USER
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;

class BSTPanel extends JPanel {

    private BSTNode root;
    private int nodeWidth = 120;
    private int nodeHeight = 40;
    private int vGap = 60; // vertical gap between levels

    public BSTPanel(BSTNode root) {
        this.root = root;
        setPreferredSize(new Dimension(1000, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            drawTree(g, root, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, BSTNode node, int x, int y, int hGap) {
        // Draw left child
        if (node.left != null) {
            int childX = x - hGap;
            int childY = y + vGap;
            g.drawLine(x, y, childX, childY);  // line to left child
            drawTree(g, node.left, childX, childY, hGap / 2);
        }

        // Draw right child
        if (node.right != null) {
            int childX = x + hGap;
            int childY = y + vGap;
            g.drawLine(x, y, childX, childY);  // line to right child
            drawTree(g, node.right, childX, childY, hGap / 2);
        }

        // Draw node
        g.setColor(new Color(200, 230, 255));
        g.fillRect(x - nodeWidth / 2, y - nodeHeight / 2, nodeWidth, nodeHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x - nodeWidth / 2, y - nodeHeight / 2, nodeWidth, nodeHeight);
        g.drawString(node.eventName + " (" + node.date + ")", x - nodeWidth / 2 + 5, y);
    }
}