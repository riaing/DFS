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

--------------------------------03.15.2019 update-----------------------------------------------
每次就去当前s的substring: 

  public class Solution {
    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>>res = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return res;
        }
        dfs(res, new ArrayList<String>(), s);
        return res;
    }
    
    private void dfs(List<List<String>>res, List<String>cur, String s) {
        if(s.equals("")) {
            List<String>copy = new ArrayList<>(cur);
            res.add(copy);
            return;
        }
        for(int i = 1; i <= s.length(); i++) {
            String substr = s.substring(0, i);
            if(isPali(substr)) {
                cur.add(substr);
                dfs(res, cur, s.substring(i, s.length()));
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    private boolean isPali(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        while(left <= right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
-------------------------------------------------- solution 2, same thoughts, different approach-----------
  https://www.youtube.com/watch?v=4ykBXGbonlA
Time 0(n!)

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(s, res, new ArrayList<String>(), 0);
        return res;   
    }
    
    
    private void dfs(String s, List<List<String>> res, List<String> cur, int startIndex) {
        if (startIndex == s.length()) {
            res.add(new ArrayList<String>(cur));
            return;
        }
        for (int i = startIndex; i< s.length(); i++) {
            String x = s.substring(startIndex, i+1);
            if (isPalindrome(x)) {
                cur.add(x);
                dfs(s, res, cur, i+1);
                cur.remove(cur.size() -1); 
            }
        }
    }
    
    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length()-1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
