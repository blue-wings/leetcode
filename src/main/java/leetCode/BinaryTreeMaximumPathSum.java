package leetCode;

/**
 * User: FR
 * Time: 1/13/15 4:21 PM
 * <p/>
 * Given a binary tree, find the maximum path sum.
 * <p/>
 * The path may start and end at any node in the tree.
 * <p/>
 * For example:
 * Given the below binary tree,
 * <p/>
 * 1
 * / \
 * 2   3
 * Return 6.
 */
public class BinaryTreeMaximumPathSum {

    private Integer maxPathSum = null;

    public int maxPathSum(TreeNode root){
        findSingleBranchMaxPathSum(root);
        return maxPathSum;
    }

    private int findSingleBranchMaxPathSum(TreeNode root) {
        int leftMaxPathSum=0;
        int rightMaxPathSum=0;
        if(root.left != null){
            leftMaxPathSum = findSingleBranchMaxPathSum(root.left);
        }
        if(root.right != null){
            rightMaxPathSum = findSingleBranchMaxPathSum(root.right);
        }
        int left = leftMaxPathSum<0?0:leftMaxPathSum;
        int right = rightMaxPathSum<0?0:rightMaxPathSum;
        if(maxPathSum == null){
            maxPathSum = root.val+left+right;
        }else if(root.val+left+right>maxPathSum){
            maxPathSum = root.val+left+right;
        }
        int single = left>right?left:right;
        return root.val+single;
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
        BinaryTreeMaximumPathSum b = new BinaryTreeMaximumPathSum();
        TreeNode root = b.new TreeNode(-1);
        TreeNode node1 = b.new TreeNode(2);
//        TreeNode node2 = b.new TreeNode(3);
//        TreeNode node3 = b.new TreeNode(4);
//        TreeNode node4 = b.new TreeNode(5);
        root.left = node1;
//        root.right=node2;
//        node1.left=node3;
//        node1.right=node4;
        System.out.println(b.maxPathSum(root));
    }
}
