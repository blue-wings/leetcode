package leetCode;

import sun.net.www.content.audio.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: FR
 * Time: 12/31/14 9:18 AM
 * <p/>
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 * 1
 * / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 */
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        if(root==null){
            return 0;
        }
        List<TreeNode> root2Leaf = new ArrayList<TreeNode>();
        root2Leaf.add(root);
        int[] sum = new int[1];
        backTrack(root, root2Leaf, sum);
        return sum[0];
    }

    private void backTrack(TreeNode node, List<TreeNode> root2Leaf, int[] sum){
        if(node.left==null && node.right==null){
            process(root2Leaf, sum);
        }
        TreeNode left = node.left;
        if(left != null){
            int size = root2Leaf.size();
            root2Leaf.add(left);
            backTrack(left, root2Leaf, sum);
            root2Leaf.remove(size);
        }
        TreeNode right = node.right;
        if(right != null){
            int size = root2Leaf.size();
            root2Leaf.add(right);
            backTrack(right, root2Leaf, sum);
            root2Leaf.remove(size);
        }
    }

    private void process(List<TreeNode> root2Leaf, int[] sum){
        int d = 0;
        for(int i=0; i<root2Leaf.size(); i++){
            d += (int)Math.pow(10, root2Leaf.size()-i-1) * root2Leaf.get(i).val;
        }
        sum[0] += d;
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
        SumRootToLeafNumbers b = new SumRootToLeafNumbers();
        TreeNode root = b.new TreeNode(1);
//        TreeNode r2 = b.new TreeNode(2);
//        TreeNode r3 = b.new TreeNode(3);
//        TreeNode r4 = b.new TreeNode(4);
//        TreeNode r5 = b.new TreeNode(5);
//        TreeNode r6 = b.new TreeNode(6);
//        root.left=r2;
//        root.right=r3;
//        r2.left=r4;
//        r2.right=r5;
//        r5.left=r6;
        System.out.println(b.sumNumbers(root));
    }
}
