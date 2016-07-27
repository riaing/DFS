Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3

//  solution 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {

       return  isSymmetric(root, root); 
    }
     public boolean isSymmetric(TreeNode q, TreeNode p){
         if (q== null && p == null){ // 递归停止的基线条件（base case）
             return true; 
         }
         if (q==null || p == null){
             return false;
         }
         if(q.val!=p.val){ //基线条件不能是q.val == p.val. 这样的话还要往下recursion
             return false; 
         }
         
         return isSymmetric(q.left, p.right) && isSymmetric(q.right , p.left);
     } 
}
