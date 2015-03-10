package leetCode;

/**
 * User: FR
 * Time: 3/10/15 4:09 PM
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class BalancedBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isBalanced(TreeNode root) {
        int b = b(root);
        if(b==-1){
            return false;
        }
        return true;
    }

    public int b(TreeNode node){
        if(node==null){
            return 0;
        }
        int lb = b(node.left);
        int rb = b(node.right);
        if(lb==-1 || rb==-1 || Math.abs(lb-rb)>1){
            return -1;
        }
        return 1+Math.max(lb,rb);
    }

    public static void main(String[] args){
        BalancedBinaryTree f = new BalancedBinaryTree();
        TreeNode root = f.new TreeNode(1);
        TreeNode node1 = f.new TreeNode(2);
        TreeNode node3 = f.new TreeNode(3);
        root.right = node1;
        node1.right=node3;
        boolean r = f.isBalanced(root);
        System.out.println(r);
    }

}
