Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.

Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].

 

Example 1:

Input: [1,17,8]
Output: 2
Explanation: 
[1,8,17] and [17,8,1] are the valid permutations.
Example 2:

Input: [2,2,2]
Output: 1
 

Note:

1 <= A.length <= 12
0 <= A[i] <= 1e9
----------------------------------DFS--------------------------------------------------------------------------------
//这个题的问题规模只有12个，也就是提醒我们可以使用O(N!)的算法，所以可以直接使用回溯法。
class Solution {
    // 注意! integer进入递归后并不会被更新，所以用一个global variable。 
    int res = 0;
    public int numSquarefulPerms(int[] A) {
        List<Integer> set = new ArrayList<Integer>(); 
        boolean[] used = new boolean[A.length];
        Arrays.sort(A); // will be easier to judge in later 
        if(A != null){
            helper(A, used, set); 
        }
        return res; 
    }
    
    // 判断是否squareful，看根号后mod的余数是否为零.注意mata.sqrt() -> double 
    private boolean squareful(int i, int j) {
        return Math.sqrt(i + j) % 1 == 0;
    }
    
    private void helper(int[] A, boolean[] used,  List<Integer> set){
        if(set.size() == A.length){
            res = res + 1;
            return;
        }
        for(int i = 0; i<A.length; i ++){    
            if(used[i] == true){
                continue;
            }
            // Avoid duplications.
            if(i!= 0 && A[i] == A[i-1] && used[i-1] == false ){ 
                continue; 
            }
            // Prune invalid solutions.
            //注意这里比较的是当前set的前一个元素和A中有可能的下一个元素，所以是set.last element vs. A[i] 
            if (!set.isEmpty() && !squareful(set.get(set.size()-1), A[i])) {
                continue; 
            }      
            set.add(A[i]);
            used[i] = true;
            helper(A, used, set);
            used[i] = false;
            set.remove(set.size()-1);
        }
    }
}
