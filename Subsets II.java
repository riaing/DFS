Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

这一题是上面Subset I的延伸，在这一题种，输入集合有重复的元素了，但是要求输出结果不能有重复的Set
例如，假设集合为［2，3，3］，如果按照Subset I的程序不做改动，会出现什么情况呢
[]
[2]
[2,3]
[2,3,3]
[2,3] //把最后一个3删去，再把倒数第二个3删去，此时集合剩下［2］，此层的循环还没完，还可以取最后一个，3，所以生成了重复的集合［2，3］
[3]
[3,3]
[3] //同理，把最后一个3删去，再把倒数第二个3删去，第一层循环还可以取最后一个数3，所以生成了重复的集合[3]
那么，我们需要做的是，在删去元素后，再取元素的时候，不要取和刚刚取过的元素相等的元素 即加上这么一条语句



public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {


	         List<List<Integer>> results = new ArrayList<List<Integer>>();
	         List<Integer> subset = new ArrayList<Integer>(); 
	         if (nums == null){
	             return results;
	         }
	         Arrays.sort(nums);
	         subsetHelper( nums, 0 , subset, results );
	            return results; 
	    } 
	    
	   private void subsetHelper(int[] nums, int index, List<Integer>subset,  List<List<Integer>> results){
            
            results.add(new ArrayList<Integer>(subset));
            
            for (int i = index ; i < nums.length; i++){
                if (i != index && nums[i] == nums[i-1]){// num[i+1] may throw nullPointerEx  判断duplicate的条件。 
                    continue; 
                }
                subset.add(nums[i]);
                subsetHelper(nums, i+1, subset, results); 
                subset.remove(subset.size() - 1);
            }
	 
	    }
	
}
        
     
