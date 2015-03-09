package leetCode;

import java.util.LinkedList;

/**
 * User: FR
 * Time: 3/6/15 3:49 PM
 *
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public boolean hasPathSum(TreeNode root, int sum) {
        return eSum(root, 0, sum);
    }

    private boolean eSum(TreeNode node, int s, int sum){
        if(node == null){
            return false;
        }
        s += node.val;
        if(node.left==null && node.right==null){
            return s==sum;
        }
        return eSum(node.left, s, sum) || eSum(node.right, s, sum);
    }
}
