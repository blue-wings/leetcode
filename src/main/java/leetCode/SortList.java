package leetCode;

/**
 * User: FR
 * Time: 10/16/14 8:58 AM
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        ListNode center = head;
        ListNode tail = head;
        while (tail.next!=null){
            if(tail.next.next==null){
                break;
            }
            tail = tail.next.next;
            center = center.next;
        }
        ListNode pre = center.next;
        center.next=null;
        ListNode head1 = mergeSort(head);
        ListNode head2 = mergeSort(pre);
        return merge(head1, head2);
    }

    private ListNode merge(ListNode head1, ListNode head2){
        ListNode head = new ListNode(0);
        ListNode p = head;
        ListNode temp = null;
        while (head1!=null && head2!=null){
            if(head2.val < head1.val){
               p.next=head2;
               if(head2.next != null){
                   temp = head2.next;
                   p.next.next = null;
                   head2 = temp;
               }else {
                   head2 = null;
               }

            }else {
                p.next=head1;
                if(head1.next != null){
                    temp = head1.next;
                    p.next.next = null;
                    head1 = temp;
                }else {
                    head1 = null;
                }

            }
            p=p.next;
        }
        if(head1!=null){
            p.next = head1;
        }
        if(head2!=null){
            p.next = head2;
        }
        return  head.next;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        int[] array = {6, 4, 1, 2, 8, 4, 7, 3, 0, 9};
        SortList sortList = new SortList();
        ListNode head = sortList.new ListNode(array[0]);
        ListNode p = head;
        for (int i = 1; i < array.length; i++) {
            ListNode node = new SortList().new ListNode(array[i]);
            p.next = node;
            p = node;
        }
        ListNode node = sortList.sortList(head);
        StringBuilder sb = new StringBuilder();
        while (node != null){
            sb.append(node.val);
            sb.append(",");
            node = node.next;
        }
        System.out.println(sb.toString());
    }
}
