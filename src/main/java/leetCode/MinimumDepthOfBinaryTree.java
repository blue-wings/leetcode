package leetCode;

import java.util.LinkedList;

/**
 * User: FR
 * Time: 3/10/15 3:32 PM
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinimumDepthOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<TreeNode> childQueue = new LinkedList<TreeNode>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            if(node.left==null && node.right==null){
                return depth;
            }
            if(node.left!=null){
                childQueue.add(node.left);
            }
            if(node.right!=null){
                childQueue.add(node.right);
            }
            if(queue.isEmpty() && !childQueue.isEmpty()){
                depth++;
                queue.addAll(childQueue);
                childQueue.clear();
            }
        }
        return depth;
    }

    public static void main(String[] args){
        MinimumDepthOfBinaryTree f = new MinimumDepthOfBinaryTree();
        TreeNode root = f.new TreeNode(1);
        TreeNode node1 = f.new TreeNode(2);
        TreeNode node2 = f.new TreeNode(5);
        TreeNode node3 = f.new TreeNode(3);
        TreeNode node4 = f.new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left=node3;
        node1.right=node4;
        int depth = f.minDepth(root);
        System.out.println(depth);
    }
}
