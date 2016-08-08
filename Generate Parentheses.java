Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

和path sum II 类似，这里只有左右括号两种选择。限制条件： 右括号不能多余左括号，invalid as ）（
两种方法： 用string和stringbuilder。 面试时可以说用stringbuilder效率更高，因为不用每次创立新的string，但是为了省时间这里用string

//    string
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String> (); 
        
        if(n >= 0 ){
            search(0, 0, "", result, n ); 
        }
        return result; 
        
    }
    
    private void search(int left, int right,  String tmp, List<String> result, int n ){
        if(left < right || left > n || right > n ) {
            return; 
        }
        
        if(left ==n && right == n ){
            result.add(tmp); 
            return; 
        }
        
        search (left+1, right, tmp+'(', result, n); // 因为string immutable，创了新的string，不需要回溯。 
        search(left, right+1, tmp+ ')', result, n) ; 
    }
}


////   stringbuilder

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String> (); 
        StringBuilder tmp = new StringBuilder() ;  //创立 
        
        if(n >= 0 ){
            search(0, 0, tmp , result, n ); 
        }
        return result; 
        
    }
    
    private void search(int left, int right,  StringBuilder tmp, List<String> result, int n ){
        if(left < right || left > n || right > n ) {
            return; 
        }
        
        if(left ==n && right == n ){
            result.add(tmp.toString());  // 转换为string 
            return; 
        }
        
        search (left+1, right, tmp.append('('), result, n);
        tmp.deleteCharAt(tmp.length() -1) ;  // 需要回溯。 记得stringbuilder用的是deleteCharAt() 
        
        search(left, right+1, tmp.append(')'), result, n) ; 
        tmp.deleteCharAt(tmp.length() -1) ; 

    }
}
