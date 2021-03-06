Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 -------------------------DFS--------
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
       
        List<Integer> result = new ArrayList<Integer>();
        if(root == null){
            return result;
        }
       
           result.add(root.val);
        dfs(root, result,1);
        return result; 
    }
    
 // 巧用depth 与 result.size()的联系，因为每一层肯定就一个元素
 //所以当递归到左边，而size和depth相等时，则不用加元素到result内
 //只有当size小余depth时，说明右边已经没有元素，这时才需要加入左边这个元素
 //注意：在进行右边递归时也要有此判断
    private void dfs(TreeNode root, List<Integer> result, int depth){
      
        if(root.left == null && root.right == null){
            return; 
        }
        
        if(root.right != null){
                if(result.size() < depth+1){ //这里也要判断，因为递归到左树的right node时
                //也要判断是否需要加入。
                result.add(root.right.val);
                
            }
            
  
            dfs(root.right, result, depth +1);
        }
        if(root.left != null){
          
            if(result.size() < depth+1){
                result.add(root.left.val);
                
            }
              dfs(root.left , result, depth +1); 
        }
    }
}

-------------------BFS---------------

//和 tree travasal， zigzag 那些题几乎一样，这里的判断条件是当是当层最后一个元素时，加到result中
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
       
        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        
        if(root == null){
            return result;
        }
        q.offer(root); 
      
        bfs(root, result, q);
        return result; 
    }

    private void bfs(TreeNode root, List<Integer> result, Queue<TreeNode> q){
      
      while(q.size() != 0){
          int size = q.size();
          for(int i = 0; i< size; i++){
              TreeNode cur = q.poll();
              
              if(cur.left != null){
                  q.offer(cur.left);
              }
              if(cur.right != null){
                  q.offer(cur.right);
              }
              
              if(i == size -1){  //此题特殊的地方
                  result.add(cur.val);
              }
          }
      }
        
    }
}
