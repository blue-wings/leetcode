package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * User: FR
 * Time: 12/5/14 11:09 AM
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {

    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    } ;

    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null){
            return null;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode p = head;
        RandomListNode headNew = new RandomListNode(head.label);
        RandomListNode pNew = headNew;
        map.put(p, pNew);
        while (p != null){
            RandomListNode next = p.next;
            if(next == null){
                break;
            }
            RandomListNode nextNew = new RandomListNode(next.label);
            pNew.next = nextNew;
            p = p.next;
            map.put(next, nextNew);
            pNew = pNew.next;
        }
        p = head;
        pNew = headNew;
        while (p != null){
            RandomListNode rand = p.random;
            RandomListNode randNew = map.get(rand);
            pNew.random = randNew;
            p = p.next;
            pNew = pNew.next;
        }
        return headNew;
    }

    public static void main(String[] args){
        CopyListWithRandomPointer c = new CopyListWithRandomPointer();
        RandomListNode n1 = c.new RandomListNode(1);
        RandomListNode n2 = c.new RandomListNode(2);
        RandomListNode n3 = c.new RandomListNode(3);
        RandomListNode n4 = c.new RandomListNode(4);
        RandomListNode n5 = c.new RandomListNode(5);
        RandomListNode n6 = c.new RandomListNode(6);
        n1.next=n2;
        n1.random = n3;
        n2.next = n3;
        n2.random = n2;
        n3.next = n4;
        n3.random = n6;
        n4.next = n5;
        n4.random=n2;
        n5.next = n6;
        n5.random=n5;
        n6.random=n1;
        RandomListNode head = c.copyRandomList(n1);
        while (head != null){
            Integer label = head.label;
            Integer nextLabel = head.next!=null?head.next.label:null;
            Integer randomLabel = head.random!=null?head.random.label:null;
            System.out.println("label:"+label+"-nextLabel:"+nextLabel+"-randomLabel:"+randomLabel);
            head=head.next;
        }
    }

}
