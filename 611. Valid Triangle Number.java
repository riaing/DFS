Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

------------------------------------------------------------------
/**
选出每三个数的组合，查看条件即可
combination (n, 3) = n*(n-1)*(n-3) / 6 = n^3 
虽然用了set优化，但还是n^3

*/
class Solution {
    int sum;
    public int triangleNumber(int[] nums) {
        sum = 0;
        if (nums == null || nums.length == 0) {
            return sum;
        }
        Arrays.sort(nums); //优化
        helper(nums, 0, new ArrayList<Integer>(), new HashSet<List<Integer>>());
        return sum;
    }
    
    private void helper(int[] nums, int index, List<Integer> cur, Set<List<Integer>> set) {
        if (set.contains(cur)) { // 优化
            sum +=1;
            return;
        }
        
        if (cur.size() == 3) {
            if (tri(cur)) {
                 sum += 1;
                set.add(new ArrayList<Integer>(cur)); //优化
            }
             return;
        } 
           
        for (int i = index; i < nums.length; i++) {
                cur.add(nums[i]);
                helper(nums, i+1, cur, set);
                cur.remove(cur.size()-1);  
        }
        
    }
    
    private boolean tri(List<Integer> cur) {
        int a = cur.get(0);
        int b = cur.get(1);
        int c = cur.get(2);
        return (a + b > c) && (b + c > a) && (a + c > b);
    }
}
---------------------Binary search -------------------------------------
/**
选出每三个数的组合，查看条件即可
N^2logN
虽然用了set优化，但还是n^3

*/
class Solution {
    
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length-2; i++) {
            int k = i + 2;
            for (int j = i+1; j < nums.length-1; j++) {
                k = binarySearch(nums[i] + nums[j], k, nums.length-1, nums);
                // 有可能k < j了。 eg[0,1,0,1]
                count += k-j-1 < 0 ? 0 : k-j-1;
            }
        }
        return count; 
    }
    
    // find the first k that i + j <= k，k might not be in the range 
    private int binarySearch(int sum, int start, int end, int[] nums) {
        if (start > end) {
            return start;
        }
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] >= sum) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        // 注意这里要特殊处理
        return nums[start] >= sum ? start : start + 1;
    }
}
