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
