Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]



public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> allsets = new ArrayList<List<Integer>>();
        List<Integer> set = new ArrayList<Integer>(); 
        boolean[] used = new boolean[32]; 
        if(nums != null){
            helper(nums, used, set, allsets); 
        }

        return allsets; 
    }
    private void helper(int[] nums, boolean[] used,  List<Integer> set, List<List<Integer>> allsets){
        
        if(set.size() == nums.length){
            allsets.add(new ArrayList<Integer>(set));
            return;
        }
        for(int i = 0; i<nums.length; i ++){    
            if(used[i] == true){
                continue;
            }
            set.add(nums[i]);
            used[i] = true;
            helper(nums, used, set , allsets);
            used[i] = false;
            set.remove(set.size()-1); 
        }
    }
}
