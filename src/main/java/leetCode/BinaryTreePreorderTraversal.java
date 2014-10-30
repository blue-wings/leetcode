package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 10/23/14 5:31 PM
 */
public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        result.add(root.val);
        if(root.left!=null){
            result.addAll(preorderTraversal(root.left));
        }
        if(root.right != null){
            result.addAll(preorderTraversal(root.right));
        }
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
        BinaryTreePreorderTraversal b = new BinaryTreePreorderTraversal();
        TreeNode root = b.new TreeNode(1);
        TreeNode right = b.new TreeNode(2);
        TreeNode left = b.new TreeNode(3);
        root.right=right;
        right.left = left;
        List<Integer> result = b.preorderTraversal(root);
        StringBuilder sb = new StringBuilder();
        for(Integer v : result){
            sb.append(v);
            sb.append(",");
        }
        System.out.println(sb.toString());
    }

}
