package leetCode;

import java.util.List;

/**
 * User: FR
 * Time: 3/26/15 3:56 PM
 *
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class MaximumDepthOfBinaryTree {
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return maxDep(root, 1);
    }

    private int maxDep(TreeNode node, int pDepth){
        int leftDep = pDepth;
        int rightDep = pDepth;
        int cDepth = ++pDepth;
        if(node.left!=null){
            leftDep = maxDep(node.left, cDepth);
        }
        if(node.right!=null){
            rightDep = maxDep(node.right, cDepth);
        }
        return leftDep>rightDep?leftDep:rightDep;
    }

    public static void main(String[] args) {
        MaximumDepthOfBinaryTree f = new MaximumDepthOfBinaryTree();
        TreeNode root = f.new TreeNode(3);
        TreeNode node1 = f.new TreeNode(9);
        TreeNode node2 = f.new TreeNode(20);
        TreeNode node3 = f.new TreeNode(15);
        TreeNode node4 = f.new TreeNode(17);

        root.left = node1;
        root.right=node2;
        node2.left = node3;
        node2.right=node4;

        int depth = f.maxDepth(root);
        System.out.println(depth);
    }
}
