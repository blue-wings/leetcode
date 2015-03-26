package leetCode;

/**
 * User: FR
 * Time: 3/26/15 4:18 PM
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return gT(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private TreeNode gT(int[] inorder, int inStart, int inEnd, int[] postorder, int pStart, int pEnd){
        if(inorder==null || postorder==null || inorder.length==0 || postorder.length==0 || inStart>inEnd || pStart>pEnd){
            return null;
        }
        int childRootVal = postorder[pEnd];
        TreeNode childRoot = new TreeNode(childRootVal);
        int childRootInOrderIndex = -1;
        for(int i=inStart; i<=inEnd; i++){
            if(inorder[i]==childRootVal){
                childRootInOrderIndex=i;
                break;
            }
        }
        if(childRootInOrderIndex==-1){
            return null;
        }
        int duration = childRootInOrderIndex-inStart;
        childRoot.left=gT(inorder, inStart, childRootInOrderIndex - 1, postorder, pStart, pStart + duration - 1);
        childRoot.right=gT(inorder, childRootInOrderIndex+1,inEnd, postorder, pStart+duration, pEnd-1);
        return childRoot;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal f = new ConstructBinaryTreeFromInorderAndPostorderTraversal();
        int[] inOrder = {-1};
        int[] pOrder = {-1};
        TreeNode root = f.buildTree(inOrder, pOrder);
    }
}
