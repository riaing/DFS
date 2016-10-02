Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

和using array来做一样，区别在：
也是要找list的中点： linkedlist用两个pointer来找到中点，array直接根据长度找
array递归时直接给出两段subarray的index即可， Linkedlist用个temp存mid前的node，然后断开形成连个sublist来递归

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
         TreeNode root= null;
         //corner case 
         if (head == null){
            return root;
        }
       
        if (head.next == null){
            root = new TreeNode(head.val);
            return root; 
        }
        //find the mid of LinkedList
        ListNode slow = head;
        ListNode quick = head; 
        ListNode tmp = slow; 
        while(quick != null && quick.next != null){ //here considered linkedlist.length == 2
            tmp =slow; 
            slow = slow.next; 
            quick = quick.next.next; 
        }
        tmp.next =null; //end of left list
        ListNode rightHead = slow.next; //create for right list 
        root = new TreeNode(slow.val); 
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(rightHead); 
        
        return root ; 
        
    }
}
