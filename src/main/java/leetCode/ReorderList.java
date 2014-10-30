package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 10/24/14 1:47 PM
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class ReorderList {

    //a simple accept solution with O(n) space
    public void reorderListSimple(ListNode head) {
        if(head == null){
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        while (head !=null ){
            list.add(head);
            head = head.next;
        }
        ListNode a = new ListNode(-1);
        for(int i=0; i<=(list.size()-1)/2; i++){
            int j = list.size() - 1- i;
            a.next = list.get(i);
            a = a.next;
            a.next = list.get(j);
            a = a.next;
            a.next=null;
        }
    }

    public void reorderList(ListNode head){
        if(head == null){
            return;
        }
        ListNode t = new ListNode(-1);
        t.next = head;
        ListNode slow = t;
        ListNode fast = t;
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
            if(fast !=null){
                fast = fast.next;
            }
        }
        ListNode tailHead = null;
        ListNode tailP1 = slow.next;
        if(tailP1 == null){
            return;
        }
        slow.next = null;
        if(tailP1.next != null){
            ListNode tailP2 = tailP1.next;
            tailP1.next=null;
            if(tailP2.next != null){
                ListNode tailP3 = tailP2.next;
                while (tailP3 != null){
                    tailP2.next = tailP1;
                    tailP2 = tailP3;
                    tailP1 = tailP2;
                    tailP3 = tailP3.next;
                }
                tailP2.next = tailP1;
            }else {
                tailP2.next = tailP1;
            }
            tailHead = tailP2;
        }else {
            tailHead = tailP1;
        };
        ListNode a = new ListNode(-1);
        while (head !=null && tailHead != null){
            a.next = head;
            head = head.next;
            a.next.next = tailHead;
            tailHead = tailHead.next;
            a=a.next.next;
        }
        if(head!=null){
            a.next = head;
        }
        if(tailHead != null){
            a.next = tailHead;
        }
        System.out.println();
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args){
        int[] array = {1,2,3,4,5};
        ReorderList reorderList = new ReorderList();
        ListNode head = reorderList.new ListNode(array[0]);
        ListNode p = head;
        for(int i=1; i<array.length; i++){
            ListNode node =reorderList. new ListNode(array[i]);
            p.next = node;
            p = node;
        }
        reorderList.reorderList(head);
        StringBuilder sb = new StringBuilder();
        while (head != null){
            sb.append(head.val);
            sb.append(",");
            head = head.next ;
        }
        System.out.println(sb.toString());
    }


}
