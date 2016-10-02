Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
addition： 注意一个BST用inorder来遍历的话，得到的是个sorted的list
这题中，因为是sorted list， 所以中间值肯定是root， 中间值的左边是left subtree， 右边是right subtree。 DFS即可
延伸：把array 换成linked list也是一样思路，考在linked list找中点
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null){
            return null;
        }
        return buildTree(nums, 0, nums.length -1); 
    }
    private TreeNode buildTree(int[] nums, int start, int end){
        if(start == end){ //actually don't need it, since've considered in recursion 
              TreeNode head  = new TreeNode(nums[start]); 
              return head;
        }
        if(start > end ){ //avoid attry index out of bound, since start + end could be negetive is don't have this condition
            return null; 
        }
        TreeNode head = new TreeNode(nums[(start + end)/2]);
        head.left = buildTree(nums, start, (start + end)/2-1);
        head.right = buildTree(nums, (start+end)/2 +1, end); 
        
        return head; 
        
    }
}
