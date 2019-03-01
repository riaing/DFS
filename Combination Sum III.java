Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]


public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> allsets = new ArrayList<List<Integer>>() ;
        List<Integer> curset = new ArrayList<Integer>(); 
        if ( n != 0){
            search(k, n , curset, allsets, 1); 
        }
        return allsets; 
    }
    
    private void search(int k, int n , List<Integer> curset, List<List<Integer>> allsets, int start ){
        if (n<0){
           return;  
        }
        if (curset.size() == k ) { 
           if(n == 0){ //注意！这里要先看size再看 结果的值，顺序不可换。因为有时结果对了但size还没到。path sum中也是一样。 
                allsets.add(new ArrayList<Integer>(curset));
            }
            return; 
        }
        for (int i = start; i<10; i++ ){
            curset.add(i); 
            search(k, n-i , curset, allsets, i+1);
            curset.remove(curset.size() -1 ); 

        }
    }
}

----------------6.13.18 update 
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (k == 0 || n == 0) {
            return results;
        }
         helper(k, n, 1, results, new ArrayList<Integer>(), 0);
        return results;
    }
    
    private void helper(int size, int target, int index, List<List<Integer>> results, List<Integer> result, int sum) {
        if (sum > target) {
            return;
        }
        if (result.size() == size && sum == target) {
             results.add(new ArrayList<Integer>(result));
            return;
        }

        for (int i = index; i <= 9; i++) {
            result.add(i);
            helper(size, target, i + 1, results, result, sum + i);
            result.remove(result.size() -1);
        }
    }
}

-------------------------2.28.19 update 比上面的要更清楚--------------------------------------
    
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> result = new ArrayList<Integer>();
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        helper(k, n, result, results, 1);
        return results;
        
    }
    
    private void helper(int k, int n, List<Integer> result, List<List<Integer>> results, int cur) {
         if (n < 0){
           return;  
        }
        if (result.size() == k && n ==0){
            results.add(new ArrayList<Integer>(result));
            return;
        }
        for (int i = cur; i <= 9; i++) {
            result.add(i);
            helper(k, n-i, result, results, i+1);
            result.remove(result.size()-1);
        }
    }
}
