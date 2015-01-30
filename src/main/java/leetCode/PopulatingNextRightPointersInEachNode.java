package leetCode;

import java.util.LinkedList;

/**
 * User: FR
 * Time: 1/22/15 11:20 AM
 *
 * Given a binary tree
 *
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Note:
 *
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 * 1
 * /  \
 * 2    3
 * / \  / \
 * 4  5  6  7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \  / \
 * 4->5->6->7 -> NULL
 */
public class PopulatingNextRightPointersInEachNode {

     public class TreeLinkNode {
         int val;
         TreeLinkNode left, right, next;
         TreeLinkNode(int x) { val = x; }
     }

    public void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }
        int k = 0;
        int length = (int)Math.pow(2, k);
        LinkedList<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.add(root);
        TreeLinkNode preNode = null;
        while (!queue.isEmpty()){
            TreeLinkNode node = queue.remove();
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
            if(preNode != null){
                preNode.next=node;
            }
            if(length == 1){
                length = (int)Math.pow(2, ++k);
                node.next=null;
                preNode = null;
            }else{
                preNode = node;
                length--;
            }
        }
    }

    public static void main(String[] args){
        PopulatingNextRightPointersInEachNode p = new PopulatingNextRightPointersInEachNode();
        TreeLinkNode node1 = p.new TreeLinkNode(1);
        TreeLinkNode node2 = p.new TreeLinkNode(2);
        TreeLinkNode node3 = p.new TreeLinkNode(3);
        TreeLinkNode node4 = p.new TreeLinkNode(4);
        TreeLinkNode node5 = p.new TreeLinkNode(5);
        TreeLinkNode node6 = p.new TreeLinkNode(6);
        TreeLinkNode node7 = p.new TreeLinkNode(7);
        node1.left = node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        p.connect(node1);
        System.out.println();
    }
}
