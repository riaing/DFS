Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]


基本思路是先排好序，然后每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题。
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allsets = new ArrayList<List<Integer>>() ; 
        List<Integer> curset = new ArrayList<Integer>() ; 
        if (candidates== null || candidates.length == 0){ 
            return allsets; 
        }
        Arrays.sort(candidates); 
        helper(candidates, target, curset, allsets, 0 );
        return allsets; 
    }
    
    private void helper(int[] candidates, int target, List<Integer> curset, 
                        List<List<Integer>> allsets, int start) {
        
        if (target == 0 ){
            allsets.add(new ArrayList(curset)); 
        }
        else if(target < 0){
            return; 
        }
       
        else{
            for (int i = start ; i< candidates.length; i ++){   // ！！ 从start开始，从0 开始像permutation
                if(i != 0 && candidates[i] == candidates[i-1]){//为了去除重复元素产生重复结果的影响，因为在这里每个数可以重复使用，所以重复的元素也就没有作用了，所以应该跳过那层递归。 
                    continue; 
                }
                 curset.add(candidates[i]);
                 helper(candidates, target - candidates[i], curset, allsets, i); //！！下层递归仍从i起，因为每个number可以重复利用。从i+1起就不能重复使用。 
                 curset.remove(curset.size() -1) ; 
            } 
        }

    } 
    
    ------------------ 6.13.18 update ---------------------------
    因为同一个元素可以重复使用，所以每次递归仍然用当前index。重要的基线条件的判断：当 sum大于target时，return
    此题要注意的是 list中的元素不能为负。 
    
    class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (candidates == null || target == 0) {
            return results;
        }
        helper(candidates, target, 0, results, new ArrayList<Integer>(), 0);
        return results;
    }
    
    private void helper(int[] candidates, int target, int index, List<List<Integer>> results, List<Integer> result, int sum) {
        if (sum > target) {
            return;
        }
        else if (sum == target) {
            results.add(new ArrayList<Integer>(result));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            result.add(candidates[i]);
            helper(candidates, target, i, results, result, sum + candidates[i]);
            result.remove(result.size() -1);
        }
    }
}
        
}
