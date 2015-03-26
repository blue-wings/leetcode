package leetCode;

import com.sun.xml.internal.ws.util.StringUtils;
import util.Print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * User: FR
 * Time: 3/17/15 10:46 AM
 *
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 3
 * / \
 * 9  20
 *     /  \
 *   15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 *
 *
 * OJ's Binary Tree Serialization:
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
 *
 * Here's an example:
 *  1
 * / \
 * 2   3
 *     /
 *   4
 *     \
 *      5
 * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 */
public class BinaryTreeLevelOrderTraversal {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null){
            return result;
        }
        List<Integer> first = new ArrayList<Integer>();
        first.add(root.val);
        result.add(first);

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<TreeNode> childrenQueue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if(node.left!=null){
                childrenQueue.add(node.left);
            }
            if(node.right!=null){
                childrenQueue.add(node.right);
            }
            if(queue.isEmpty() && !childrenQueue.isEmpty()){
                List<Integer> childrenResult = new ArrayList<Integer>(childrenQueue.size());
                for(TreeNode child : childrenQueue){
                    queue.add(child);
                    childrenResult.add(child.val);
                }
                result.add(childrenResult);
                childrenQueue.clear();
            }
        }
        return result;
    }

    public static void main(String[] args){
        BinaryTreeLevelOrderTraversal f = new BinaryTreeLevelOrderTraversal();
        TreeNode root = f.new TreeNode(3);
        TreeNode node1 = f.new TreeNode(9);
        TreeNode node2 = f.new TreeNode(20);
        TreeNode node3 = f.new TreeNode(15);
        TreeNode node4 = f.new TreeNode(17);

        root.left = node1;
        root.right=node2;
        node2.left = node3;
        node2.right=node4;

        List<List<Integer>> result = f.levelOrder(root);
        System.out.println();

    }
}
