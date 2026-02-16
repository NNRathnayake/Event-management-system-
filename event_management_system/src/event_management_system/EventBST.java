/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EventBST {

    // Inner class for BST nodes
    public static class BSTNode {
        String eventId;
        String eventName;
        Date date;
        BSTNode left, right;

        public BSTNode(String eventId, String eventName, Date date) {
            this.eventId = eventId;
            this.eventName = eventName;
            this.date = date;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return date + " - " + eventName;
        }
    }

    // Root node of the BST
    public BSTNode root;

    // === Insert Event ===
    public void insert(String eventId, String eventName, Date date) {
        root = insertRec(root, eventId, eventName, date);
    }

    private BSTNode insertRec(BSTNode node, String eventId, String eventName, Date date) {
        if (node == null) {
            return new BSTNode(eventId, eventName, date);
        }

        if (date.before(node.date)) {
            node.left = insertRec(node.left, eventId, eventName, date);
        } else {
            node.right = insertRec(node.right, eventId, eventName, date);
        }

        return node;
    }

    // === Get All Events in Chronological Order (InOrder Traversal) ===
    public List<BSTNode> inOrderTraversal() {
        List<BSTNode> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(BSTNode node, List<BSTNode> list) {
        if (node != null) {
            inOrderRec(node.left, list);
            list.add(node);
            inOrderRec(node.right, list);
        }
    }

    // === DFS Traversals ===

    // InOrder DFS
    public void dfsInOrder() {
        System.out.println("DFS InOrder:");
        dfsInOrderRec(root);
    }

    private void dfsInOrderRec(BSTNode node) {
        if (node != null) {
            dfsInOrderRec(node.left);
            System.out.println(node);
            dfsInOrderRec(node.right);
        }
    }

    // PreOrder DFS
    public void dfsPreOrder() {
        System.out.println("DFS PreOrder:");
        dfsPreOrderRec(root);
    }

    private void dfsPreOrderRec(BSTNode node) {
        if (node != null) {
            System.out.println(node);
            dfsPreOrderRec(node.left);
            dfsPreOrderRec(node.right);
        }
    }

    // PostOrder DFS
    public void dfsPostOrder() {
        System.out.println("DFS PostOrder:");
        dfsPostOrderRec(root);
    }

    private void dfsPostOrderRec(BSTNode node) {
        if (node != null) {
            dfsPostOrderRec(node.left);
            dfsPostOrderRec(node.right);
            System.out.println(node);
        }
    }
}