package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 10/22/14 1:23 PM
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 */
public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root.left!=null){
            result.addAll(postorderTraversal(root.left));
        }
        if(root.right != null){
            result.addAll(postorderTraversal(root.right));
        }
        result.add(root.val);
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

    }

    public static void main(String[] args){
        BinaryTreePostorderTraversal b = new BinaryTreePostorderTraversal();
        TreeNode root = b.new TreeNode(1);
        TreeNode right = b.new TreeNode(2);
        TreeNode left = b.new TreeNode(3);
        root.right=right;
        right.left = left;
        List<Integer> result = b.postorderTraversal(root);
        StringBuilder sb = new StringBuilder();
        for(Integer v : result){
            sb.append(v);
            sb.append(",");
        }
        System.out.println(sb.toString());
    }
}
