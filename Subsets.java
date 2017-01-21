
Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

///单纯递归解法，类似树的求path题，其实就是在原有的集合中对每集合中的每个元素都加入新元素得到子集，
然后放入原有集合中（原来的集合中的元素不用删除，因为他们也是合法子集）。
而结束条件就是如果没有元素就返回空集（注意空集不是null，而是没有元素的数组）就可以了。时间和空间都是取决于结果的数量，也就是O(2^n)
public class Solution {
    	public List<List<Integer>> subsets(int[] nums) {
		if(nums == null){
			return null; 
		}
		Arrays.sort(nums); 
		return subsets(nums, 0 );   
	 
          
        } 
   //类似path sum的解法，求出所有子集的subsets，每个子集list前面加上head。
   private List<List<Integer>> subsets(int[] nums, int index){
	 //  List<List<Integer>> subset = new ArrayList<ArrayList<Integer>>();
       
       
      if(index == nums.length){  
    	  List<Integer> cur = new ArrayList<Integer>(); 
    	  List<List<Integer>> subset = new ArrayList<List<Integer>>();  // + 
    	  subset.add(cur); 
    	  return subset; 
      }
       
       //subset.addAll(subsets(nums, index +1)); 
        List<List<Integer>> subset = subsets(nums, index +1);
       
       int tmp = subset.size(); 
       for ( int i = 0 ; i < tmp; i ++){
           List<Integer > original = new ArrayList<Integer>(subset.get(i)); //!!不能直接等于 subset.get
           original.add(nums[index]);
           subset.add(original);
       }
       


       return subset; 
   }
}


//DFS 解法： 
这道题是要求生成所有子集，那么首先我们有一个能返回所有子集的ArrayList res, 和一个临时变量ArrayList tmp, 当tmp满足一定条件的时候，往res里面添加结果
Subset这道题的条件比较直观，就是每当我们添加了一个元素，都是一个新的子集。那么我们怎么保证不会出现重复集合呢。我们引入一个int pos用来记录此子集的起点在哪，比如当pos = 1的时候就是从第二个元素往后循环添加元素（0 base）,每次当此层用了第i个元素，那么下一层需要传入下一个元素的位置i+1 除此之外，当循环结束要返回上一层dfs的时候我们需要把这一层刚添加元素删去。
比如输入集合为［1，2，3］应当是这么运行，
[]
[1]
[1,2]
[1,2,3] //最底层子循环到头返回删去3，上一层的子循环也到头删去2
          //而此时，这一层循环刚到2，删去后还可以添加一个3
[1,3] //删除3，删除1
[2]
[2,3] //删除3，删除2
[3]

注意： 
结果要求生成升序排列，所以最开始的时候我们需要Sort一下
2. 往res里面添加的时候要 new ArrayList(tmp);
3. 别忘了空集也是子集

public class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
public List<List<Integer>> subsets(int[] nums) {
	         List<List<Integer>> results = new ArrayList<List<Integer>>();
	         List<Integer> subset = new ArrayList<Integer>(); 
	         if (nums == null){
	             return results;
	         }
	         Arrays.sort(nums);
	         subsetHelper( nums, 0 , subset, results );
	            return results; 
	    } 
	    
	   private void subsetHelper(int[] nums, int index, List<Integer> subset,  List<List<Integer>> results){
            
            results.add(new ArrayList<Integer>(subset));
            
            for (int i = index ; i < nums.length; i++){
                subset.add(nums[i]);
                subsetHelper(nums, i+1, subset, results); 
                subset.remove(subset.size() - 1);
            }
	 
	    }
	
}


###DFS ----- 每个element 只有加，或不加，
public class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
public List<List<Integer>> subsets(int[] nums) {
	         List<List<Integer>> results = new ArrayList<List<Integer>>();
	         List<Integer> subset = new ArrayList<Integer>(); 
	         if (nums == null){
	             return results;
	         }
	      
	        subsetHelper( nums, 0 , subset, results );
	            return results; 
	    } 
	    
	   private void subsetHelper(int[] nums, int index, List<Integer> subset,  List<List<Integer>> results){
            
            if ( index == nums.length ){
               results.add(new ArrayList<Integer>(subset)); 
               return; 
            }
            
            subsetHelper(nums, index+1, subset, results);
	    
            subset.add(nums[index])；
            subsetHelper(nums, index+1, subset, results);
            subset.remove(subset.size()-1); 
           
            
            
         
	 
	  }
	
}
