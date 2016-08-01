
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

在找结果的时候看他是不是跟前一个元素相同。
 这道题还要考虑的是visited情况，前一个元素就算跟当前元素相同，如果visited==true也没关系。但是如果前面元素跟当前元素相同还没被visited，那么就要做去重处理了。
验：
如果未加visited判断的话，将会出现下面的错误：
Input:
[1,1]
Output:
[]
Expected:
[[1,1]]
因为执行了去重处理，所以一个结果都没有保留
 与combination不同的两处 ： 
这里在每次添加遍历的item时候，要判断该元素是否之前被visited过，来避免产生重复。
另外一个是，for循环的起始是start，而非每次从0开始，不然的话，会忽略掉start位置之前，未visited过的，非重复值。
比如： [1, 2]，第一次记录结果[1,2]是正常的没有问题。 但是当退栈到第一个栈，走for循环时，item位置的第一个元素是2, 进入下一层递归，发现start位置是1（0+1），1位置上面是元素2，2被visited过了，所以就结束了这个程序。那么位置在0的，值为1的元素，就被忽略掉了。因为start位置没有从0开始，所以每次都应该从0位置开始。 



public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> allsets = new ArrayList<List<Integer>>();
        List<Integer> set = new ArrayList<Integer>(); 
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums); // will be easier to judge in later 
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
            if(i!= 0 && nums[i] == nums[i-1] && used[i-1] == false ){ // condition for duplicates 
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

