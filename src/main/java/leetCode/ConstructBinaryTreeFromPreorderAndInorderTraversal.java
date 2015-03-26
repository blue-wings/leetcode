package leetCode;

/**
 * User: FR
 * Time: 3/26/15 4:09 PM
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return gT(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    private TreeNode gT(int[] preOrder, int pStart, int pEnd, int[] inOrder, int inStart, int inEnd){
        if(preOrder==null || inOrder==null || preOrder.length==0 || inOrder.length==0 || inStart>inEnd || pStart>pEnd){
            return null;
        }
        int childRootVal = preOrder[pStart];
        TreeNode childRoot = new TreeNode(childRootVal);
        int childRootInOrderIndex = -1;
        for(int i=inStart; i<=inEnd; i++){
            if(inOrder[i]==childRootVal){
                childRootInOrderIndex=i;
                break;
            }
        }
        if(childRootInOrderIndex==-1){
            return null;
        }
        int duration = childRootInOrderIndex-inStart;
        childRoot.left=gT(preOrder, pStart+1, pStart+duration, inOrder, inStart, childRootInOrderIndex-1);
        childRoot.right=gT(preOrder, pStart+duration+1,pEnd, inOrder, childRootInOrderIndex+1, inEnd);
        return childRoot;
    }
}
