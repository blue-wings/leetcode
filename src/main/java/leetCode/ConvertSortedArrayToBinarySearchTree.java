package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 3/11/15 5:40 PM
 */
public class ConvertSortedArrayToBinarySearchTree {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public TreeNode sortedArrayToBST(int[] num) {
        if(num.length==0){
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();
       for(int n : num){
            list.add(n);
        }
        TreeNode root = convert(list, 0, list.size()-1);
        return root;
    }

    private TreeNode convert(List<Integer> list, int start, int end){
        if(start>end){
            return null;
        }
        int mid = start + (end-start)/2;
        TreeNode parent = new TreeNode(list.get(mid));
        TreeNode left = convert(list, start, mid-1);
        parent.left=left;
        TreeNode right = convert(list, mid+1, end);
        parent.right=right;
        return parent;
    }

}
