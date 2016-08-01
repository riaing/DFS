Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]


public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result  = new ArrayList<List<Integer>>() ; 
        List<Integer> cur = new ArrayList<Integer>(); 
        if ( k == 0  || n == 0  ){
             return result; 
        }
        helper(n, k, 1, cur, result); 
        return result; 
    }
    
    private void helper(int list, int combination, int index,
                        List<Integer> cur, List<List<Integer>> result){
        if (cur.size() == combination){ //if size == k. 
            result.add(new ArrayList<Integer>(cur)); 
        }
        for ( int i = index; i<=list; i ++ ){
            cur.add(i);
            helper(list, combination, i+1, cur, result);
            cur.remove(cur.size()-1);
        }
    }
    
    
}
