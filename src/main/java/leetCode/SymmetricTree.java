package leetCode;

import java.util.LinkedList;

/**
 * User: FR
 * Time: 3/27/15 4:50 PM
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree is symmetric:
 *
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * But the following is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 *
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 *
 *
 * OJ's Binary Tree Serialization:
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
 *
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
public class SymmetricTree {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<TreeNode> childrenQueue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            if(node==null || node.left==null){
                childrenQueue.add(null);
            }else{
                childrenQueue.add(node.left);
            }
            if(node==null || node.right==null){
                childrenQueue.add(null);
            }else{
                childrenQueue.add(node.right);
            }
            if(queue.isEmpty() && !childrenQueue.isEmpty()){
                for(int i=0; i<childrenQueue.size(); i++){
                    //上级对称，下级只需要验证不为空的节点是否对称
                    if(childrenQueue.get(i)!=null){
                        queue.add(childrenQueue.get(i));
                    }
                    if(i<childrenQueue.size()/2){
                        int j = childrenQueue.size()-i-1;
                        if(childrenQueue.get(i)==null && childrenQueue.get(j)==null){
                            continue;
                        }
                        if((childrenQueue.get(i)==null && childrenQueue.get(j)!=null) || (childrenQueue.get(i)!=null && childrenQueue.get(j)==null)){
                            return false;
                        }
                        if(childrenQueue.get(i).val == childrenQueue.get(j).val){
                            continue;
                        }
                        return false;
                    }
                }
                childrenQueue.clear();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SymmetricTree s = new SymmetricTree();
        TreeNode root = s.new TreeNode(1);
        TreeNode node1 = s.new TreeNode(2);
        TreeNode node2 = s.new TreeNode(2);
        TreeNode node3 = s.new TreeNode(3);
        TreeNode node4 = s.new TreeNode(3);
        root.left=node1;
        root.right=node2;
        node1.right=node3;
        node2.left=node4;
        System.out.println(s.isSymmetric(root));
    }
}
