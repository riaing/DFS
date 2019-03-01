Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

//ex： 【1，1，2，5，6，7，10】 target = 8; 
//return [1,1,6] , [2,6], [1,7] , [1,2,5] 

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> allsets = new ArrayList< List<Integer>>() ;
        List< Integer> curset = new ArrayList<Integer>() ; 
        if (!(candidates == null && candidates.length == 0)){
            Arrays.sort(candidates); //
            search(candidates, target, curset, allsets, 0); 
        }
        return allsets; 
    }
    
    private void search(int[] candidates, int target, List< Integer> curset,
                        List<List<Integer>> allsets, int start){
        if (target<0){
            return;
        }
        if (target == 0){
            allsets.add(new ArrayList<Integer>(curset));
        }
        else{ 
        for (int i = start; i<candidates.length; i++){
            if(i != start && candidates[i] == candidates[i-1]){// 一定要有i != start,第一次还是要用，用过之后就去重。 没有的话将显示不出【1，1，6】
                continue; 
            }
            //没有这个去重判断的话，[[1,1,6],[1,2,5],[1,7],[1,2,5],[1,7],[2,6]]
            curset.add(candidates[i]);
       
            search(candidates, target-candidates[i], curset, allsets, i+1);
            curset.remove(curset.size() -1); 
        }
        } 
    }
}

--------------6.13.18 update  --------------------
  要求不能有重复元素， key在于当sort 和判断当当前元素和list中前一个元素一样时，跳过 class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (candidates == null || target == 0) {
            return results;
        }
        Arrays.sort(candidates);
         helper(candidates, target, 0, results, new ArrayList<Integer>(), 0);
        return results;
    }
    
    private void helper(int[] candidates, int target, int index, List<List<Integer>> results, List<Integer> result, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            results.add(new ArrayList<Integer>(result));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i-1]) {
                continue;
            }
            result.add(candidates[i]);
            helper(candidates, target, i + 1, results, result, sum + candidates[i]);
            result.remove(result.size() -1);
        }
    }
    
}
 -------------2.28.19 update 还是要注意 i！=index，因为第一次用完后才跳过接下来重复的元素----------------
   class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) {
            return results; 
        }
        Arrays.sort(candidates);
        helper(results, new ArrayList<Integer>(), candidates, target, 0); 
        return results;
    }
    
    private void helper(List<List<Integer>> results, List<Integer> result, int[] candidates, int target, int cur) {
        if (target == 0) {
            results.add (new ArrayList<>(result));
            return;
        }
        for (int i = cur; i < candidates.length; i ++) {
            if (candidates[i] > target){
                continue;
            }
            if (i != cur && candidates[i] == candidates[i-1]) {
                continue;
            }
            result.add(candidates[i]);
            helper(results, result, candidates, target - candidates[i], i+1);
            result.remove(result.size()-1);
        }
    }
        
}
