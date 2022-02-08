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


-----------------------6.21 update -----------------
  通过向string插入"("和")"直到两者的数量都为n，则一个combination构建完成。如何保证这个combination是well-formed？在插入过程中的任何时候：

1. 只要"("的数量没有超过n，都可以插入"("。
2. 而可以插入")"的前提则是当前的"("数量必须要多余当前的")"数量。



class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        StringBuilder result = new StringBuilder();
        if (n <= 0) {
            return results;
        }
        helper(n, result, results, 0, 0);
        return results;
    }
    private void helper(int n, StringBuilder result, List<String> results, int nLeft, int nRight) {
        if (nLeft == n && nRight == n) {
            results.add(result.toString());
            return;
        }
        
        if (nLeft < n) {
            result.append("(");
            helper(n, result, results, nLeft+1, nRight);
            result.deleteCharAt(result.length() - 1);
        }
        
        if(nRight < nLeft) {
            result.append(")");
            helper(n, result, results, nLeft, nRight+1);
            result.deleteCharAt(result.length() - 1);
        }
    }
}

------------------------- 2/7/2022 -----------------------------------------------------------
  /*
time O(n*2^n) -> O(n) for creating string 
space (n * 2^N)
*/
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        helper(n, 0, 0, results, "");
        return results; 
    }
    
    private void helper(int n, int left, int right, List<String> results, String cur) { 
        if (right > left || right > n || left > n) {
            return;
        }
        if (right == n && left == n) {
            results.add(cur);
            return;
        }
        
        helper(n, left+1, right, results, cur + "(");
        helper(n, left, right+1, results, cur + ")");
        return;
    }
}
