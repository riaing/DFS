Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]



和Restore IP Address结构一模一样
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> sublist = new ArrayList<String>();
        
        if (s == null || s.length() ==0){
            return result;
        }
        
        helper(s, 0, sublist, result);
        return result;
        
    }
    
    private void helper(String s, int start, List<String> sublist, List<List<String>> result ){
          if (start == s.length()){   //判断结束条件
             result.add(new ArrayList<String> (sublist)); 
             return; 
          }
          
          for(int i = start; i < s.length(); i++ ) { 
              String tmp = s.substring(start, i+1); //这个tmp的长度可以是1， 2，3。。。所以用substring能不用每次append一个char
              if(isValid(tmp)){   //类似restore IP Adrress，判断这个substring是不是valid 
                  sublist.add(tmp);  //先加上这个substring
                  helper(s, i+1, sublist, result);  //第二层递归
                  sublist.remove(sublist.size() -1); // 返回第一层，DFS结束
              }
          }
    }
    
    private boolean isValid(String tmp){ //判断条件
        int i = 0;
        int j = tmp.length()-1; 
        while(i<=j){
            if(tmp.charAt(i)!= tmp.charAt(j)){
                return false;
            }
            i++;
            j--; 
        }
        return true; 
        
    }
}
