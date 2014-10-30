package leetCode;

/**
 * User: FR
 * Time: 10/20/14 2:42 PM
 * Sort a linked list using insertion sort.
 */
public class InsertionSort {
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode p = head.next;
        head.next = null;
        while (p!=null) {
            ListNode temp = p.next;
            ListNode sortP = head;
            ListNode sortPParent = null;
            int i = 0;
            while (sortP!=null){
                if(p.val < sortP.val){
                    if(sortPParent !=null){
                        sortPParent.next = p;
                    }
                    p.next = sortP;
                    if(i == 0){
                        head = p;
                    }
                    break;
                }
                sortPParent = sortP;
                if(sortP.next == null){
                    sortP.next=p;
                    p.next=null;
                    break;
                }
                sortP=sortP.next;
                i++;
            }
           p = temp;
        }
        return head;
    }

    public static void main(String[] args){
        int[] array = {5, 88, 24, 3, 29};
        InsertionSort insertionSort = new InsertionSort();
        ListNode head = insertionSort.new ListNode(array[0]);
        ListNode p = head;
        for(int i=1; i<array.length; i++){
            ListNode node =insertionSort. new ListNode(array[i]);
            p.next = node;
            p = node;
        }
        head = insertionSort.insertionSortList(head);
        StringBuilder sb = new StringBuilder();
        while (head != null){
            sb.append(head.val);
            sb.append(",");
            head = head.next ;
        }
        System.out.println(sb.toString());
    }

}
