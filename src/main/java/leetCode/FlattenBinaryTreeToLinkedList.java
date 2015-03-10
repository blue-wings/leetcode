package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 3/10/15 2:37 PM
 *
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example,
 * Given
 *
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * click to show hints.
 *
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
 */
public class FlattenBinaryTreeToLinkedList {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        f(root, nodeList);
        TreeNode pre = null;
        for(TreeNode node : nodeList){
            if(pre != null){
                pre.left=null;
                pre.right=node;
            }
            pre = node;
        }
    }

    private void f(TreeNode node, List<TreeNode> nodeList){
        nodeList.add(node);
        if(node.left != null){
            f(node.left, nodeList);
        }
        if(node.right!=null){
            f(node.right, nodeList);
        }
    }

   public static void main(String[] args){
        FlattenBinaryTreeToLinkedList f = new FlattenBinaryTreeToLinkedList();
       TreeNode root = f.new TreeNode(1);
       TreeNode node1 = f.new TreeNode(2);
       TreeNode node2 = f.new TreeNode(5);
       TreeNode node3 = f.new TreeNode(3);
       TreeNode node4 = f.new TreeNode(4);
       TreeNode node5 = f.new TreeNode(6);
       root.left = node1;
       root.right = node2;
       node1.left=node3;
       node1.right=node4;
       node2.right=node5;
       f.flatten(root);
       System.out.println();
   }
}
