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

还是用一个循环递归处理子问题。区别是这里并不是一直往后推进的，前面的数有可能放到后面，
所以我们需要维护一个used数组来表示该元素是否已经在当前结果中，因为每次我们取一个元素放入结果，
然后递归剩下的元素，所以不会出现重复。时间复杂度还是NP问题的指数量级。

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> allsets = new ArrayList<List<Integer>>();
        List<Integer> set = new ArrayList<Integer>(); 
        boolean[] used = new boolean[nums.length]; 
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
