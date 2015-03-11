package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 3/11/15 3:39 PM
 *
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedListToBinarySearchTree {

     public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; next = null; }
     }

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public TreeNode sortedListToBST(ListNode head) {
        if(head==null){
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();
        ListNode p = head;
        while (p!=null){
            list.add(p.val);
            p = p.next;
        }
        TreeNode root = convert(list, 0, list.size()-1);
        return root;
    }

    private TreeNode convert(List<Integer> list, int start, int end){
        if(start>end){
            return null;
        }
        int mid = start + (end-start)/2;
        TreeNode parent = new TreeNode(list.get(mid));
        TreeNode left = convert(list, start, mid-1);
        parent.left=left;
        TreeNode right = convert(list, mid+1, end);
        parent.right=right;
        return parent;
    }

    public static void main(String[] args){
        ConvertSortedListToBinarySearchTree c = new ConvertSortedListToBinarySearchTree();
        ListNode node1 = c.new ListNode(1);
        ListNode node2 = c.new ListNode(2);
        ListNode node3 = c.new ListNode(3);
        ListNode node4 = c.new ListNode(4);
        ListNode node5 = c.new ListNode(5);
        ListNode node6 = c.new ListNode(6);
        ListNode node7 = c.new ListNode(7);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        TreeNode root = c.sortedListToBST(node1);
        System.out.println();
    }
}
