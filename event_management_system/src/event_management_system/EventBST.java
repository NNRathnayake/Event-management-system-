/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_management_system;

/**
 *
 * @author USER
 */
class EventBST {
    BSTNode root;

    void insert(String eventId, String eventName, java.sql.Date date) {
        root = insertRec(root, eventId, eventName, date);
    }

    private BSTNode insertRec(BSTNode root, String eventId, String eventName, java.sql.Date date) {
        if (root == null) {
            return new BSTNode(eventId, eventName, date);
        }
        if (date.before(root.date)) {
            root.left = insertRec(root.left, eventId, eventName, date);
        } else {
            root.right = insertRec(root.right, eventId, eventName, date);
        }
        return root;
    }

    void inOrderTraversal(java.util.List<BSTNode> list) {
        inOrderRec(root, list);
    }

    private void inOrderRec(BSTNode node, java.util.List<BSTNode> list) {
        if (node != null) {
            inOrderRec(node.left, list);
            list.add(node);
            inOrderRec(node.right, list);
        }
    }
}

//store eventid , eventname , date . then insert to tree,then that insertrec is recursive function
//If the event’s date is before the current node’s date → go left

//3. inOrderTraversal(java.util.List<BSTNode> list)

//This is used to get all events in chronological order.