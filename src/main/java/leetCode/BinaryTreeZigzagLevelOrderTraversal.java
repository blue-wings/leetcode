package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: FR
 * Time: 3/26/15 3:27 PM
 * <p/>
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p/>
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 3
 * / \
 * 9  20
 *     /  \
 *   15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * <p/>
 * <p/>
 * OJ's Binary Tree Serialization:
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
 * <p/>
 * Here's an example:
 * 1
 * / \
 * 2   3
 * /
 * 4
 * \
 * 5
 * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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
        int i =1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if(node.left!=null){
                childrenQueue.add(node.left);
            }
            if(node.right!=null){
                childrenQueue.add(node.right);
            }
            if(queue.isEmpty() && !childrenQueue.isEmpty()){
                queue.addAll(childrenQueue);
                LinkedList<Integer> childrenResult = new LinkedList<Integer>();
                boolean flag = i%2==0?true:false;
                for(TreeNode child : childrenQueue){
                    if(flag){
                        childrenResult.add(child.val);
                    }else{
                        childrenResult.push(child.val);
                    }
                }
                result.add(childrenResult);
                childrenQueue.clear();
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeZigzagLevelOrderTraversal f = new BinaryTreeZigzagLevelOrderTraversal();
        TreeNode root = f.new TreeNode(3);
        TreeNode node1 = f.new TreeNode(9);
        TreeNode node2 = f.new TreeNode(20);
        TreeNode node3 = f.new TreeNode(15);
        TreeNode node4 = f.new TreeNode(17);

        root.left = node1;
        root.right=node2;
        node2.left = node3;
        node2.right=node4;

        List<List<Integer>> result = f.zigzagLevelOrder(root);
        System.out.println();
    }
}
