package leetCode;

/**
 * User: FR
 * Time: 10/28/14 9:20 AM
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null){
            if(slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
            }
        }
        return false;
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
        LinkedListCycle linkedListCycle = new LinkedListCycle();
        ListNode node1 = linkedListCycle.new ListNode(1);
        ListNode node2 = linkedListCycle.new ListNode(2);
        ListNode node3 = linkedListCycle.new ListNode(3);
        ListNode node4 = linkedListCycle.new ListNode(4);
        ListNode node5 = linkedListCycle.new ListNode(5);
        ListNode node6 = linkedListCycle.new ListNode(6);

        node1.next = node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node3;
        System.out.println(linkedListCycle.hasCycle(node1));
    }
}
