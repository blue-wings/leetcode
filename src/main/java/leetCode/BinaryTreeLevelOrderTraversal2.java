package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: FR
 * Time: 3/26/15 2:30 PM
 *
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 3
 * / \
 * 9  20
 *     /  \
 *   15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


 * OJ's Binary Tree Serialization:
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

 * Here's an example:
 * 1
 * / \
 * 2   3
 *     /
 *   4
 *     \
 *      5
 * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 */
public class BinaryTreeLevelOrderTraversal2 {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root==null){
            return result;
        }
        List<Integer> rootValue = new ArrayList<Integer>();
        rootValue.add(root.val);
        result.push(rootValue);
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<TreeNode> childrenQueue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            if(node.left != null){
                childrenQueue.add(node.left);
            }
            if(node.right!=null){
                childrenQueue.add(node.right);
            }
            if(queue.isEmpty() && !childrenQueue.isEmpty()){
                queue.addAll(childrenQueue);
                List<Integer> childValue = new ArrayList<Integer>();
                for(TreeNode child : childrenQueue){
                    childValue.add(child.val);
                }
                result.push(childValue);
                childrenQueue.clear();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal2 f = new BinaryTreeLevelOrderTraversal2();
        TreeNode root = f.new TreeNode(3);
        TreeNode node1 = f.new TreeNode(9);
        TreeNode node2 = f.new TreeNode(20);
        TreeNode node3 = f.new TreeNode(15);
        TreeNode node4 = f.new TreeNode(17);

        root.left = node1;
        root.right=node2;
        node2.left = node3;
        node2.right=node4;

        List<List<Integer>> result = f.levelOrderBottom(root);
        System.out.println();
    }

}
