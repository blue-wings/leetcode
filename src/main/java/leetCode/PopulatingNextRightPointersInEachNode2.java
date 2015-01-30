package leetCode;

import java.util.LinkedList;

/**
 * User: FR
 * Time: 1/22/15 11:20 AM
 *
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 *
 * What if the given tree could be any binary tree? Would your previous solution still work?
 *
 * Note:
 *
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \    \
 * 4-> 5 -> 7 -> NULL
 */
public class PopulatingNextRightPointersInEachNode2 {

     public class TreeLinkNode {
         int val;
         TreeLinkNode left, right, next;
         TreeLinkNode(int x) { val = x; }
     }

    public void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }
        LinkedList<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        LinkedList<TreeLinkNode> childQueue = new LinkedList<TreeLinkNode>();
        queue.add(root);
        TreeLinkNode preNode = null;
        while (!queue.isEmpty()){
            TreeLinkNode node = queue.remove();
            if(preNode != null){
                preNode.next=node;
            }
            preNode = node;
            if(node.left != null){
                childQueue.add(node.left);
            }
            if(node.right!=null){
                childQueue.add(node.right);
            }
            if(queue.isEmpty() && !childQueue.isEmpty()){
                queue.addAll(childQueue);
                childQueue.clear();
                preNode=null;
            }
        }
    }

    public static void main(String[] args){
        PopulatingNextRightPointersInEachNode2 p = new PopulatingNextRightPointersInEachNode2();
        TreeLinkNode node1 = p.new TreeLinkNode(1);
        TreeLinkNode node2 = p.new TreeLinkNode(2);
        TreeLinkNode node3 = p.new TreeLinkNode(3);
        TreeLinkNode node4 = p.new TreeLinkNode(4);
        TreeLinkNode node5 = p.new TreeLinkNode(5);
        TreeLinkNode node7 = p.new TreeLinkNode(7);
        node1.left = node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.right=node7;
        p.connect(node1);
        System.out.println();
    }
}
