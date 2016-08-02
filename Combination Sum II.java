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
