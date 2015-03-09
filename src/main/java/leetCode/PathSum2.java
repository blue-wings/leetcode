package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 3/6/15 4:39 PM
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 */
public class PathSum2 {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results  = new ArrayList<List<Integer>>();
        if(root==null){
            return results;
        }
        List<Integer> result = new ArrayList<Integer>();
        result.add(root.val);
        eSum(root, root.val, sum, result, results);
        return results;
    }

    private void eSum(TreeNode node, int s, int sum, List<Integer> result, List<List<Integer>> results){
        if(node.left==null && node.right==null){
            if(s == sum){
                List<Integer> r = new ArrayList<Integer>();
                for(Integer e : result){
                    r.add(e);
                }
                results.add(r);
            }
            return;
        }
        if(node.left != null){
            s+=node.left.val;
            result.add(node.left.val);
            int length = result.size()-1;
            eSum(node.left, s, sum, result, results);
            s-=node.left.val;
            result.remove(length);
        }
        if(node.right != null){
            s+=node.right.val;
            result.add(node.right.val);
            int length = result.size()-1;
            eSum(node.right, s, sum, result, results);
            result.remove(length);
        }
    }

    public static void main(String[] args){
        PathSum2 pathSum2 = new PathSum2();
        TreeNode root = pathSum2.new TreeNode(5);
        TreeNode node1 = pathSum2.new TreeNode(4);
        TreeNode node2 = pathSum2.new TreeNode(8);
        TreeNode node3 = pathSum2.new TreeNode(11);
        TreeNode node4 = pathSum2.new TreeNode(13);
        TreeNode node5 = pathSum2.new TreeNode(4);
        TreeNode node6 = pathSum2.new TreeNode(7);
        TreeNode node7 = pathSum2.new TreeNode(2);
        TreeNode node8 = pathSum2.new TreeNode(5);
        TreeNode node9 = pathSum2.new TreeNode(1);

        root.left=node1;
        root.right=node2;
        node1.left=node3;
        node3.left=node3;
        node3.left=node6;
        node3.right=node7;
        node2.left=node4;
        node2.right=node5;
        node5.left=node8;
        node5.right=node9;

        List<List<Integer>> results = pathSum2.pathSum(root, 22);
        System.out.println("");
    }

}
