Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10


-------------------------recursion-------------------------------------------------
/**
将符号看做分割点，循环数组，碰到符号时，递归符号左右两边，得到所有可能的解，再组合运算加到result中
time：http://people.math.sc.edu/howard/Classes/554b/catalan.pdf 是个阶乘
**/
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
    
        return dfs(input);
    }
    
    //面试中要考虑用set进行去重
    private List<Integer> dfs(String input) {
         List<Integer> res = new ArrayList<Integer>();
      
        for (int i = 0; i < input.length(); i++) {
            //如果找到符号，分别递归符号左边和右边，找到所有返回的值，再根据这个符号运算，加到返回的集合中
            if (isSymbol(input.charAt(i))) {
                List<Integer> right = dfs(input.substring(0, i)); 
                List<Integer> left = dfs(input.substring(i+1));
 
                //这里拿到左右两边返回的值，进行组合运算
                for (int r = 0; r < right.size(); r++){
                    for (int l = 0; l < left.size(); l++) {
                        int rightValue = right.get(r);
                        int leftValue = left.get(l);
                        if (input.charAt(i) == '+') {
                             res.add(rightValue + leftValue);
                        }
                        if (input.charAt(i) == '-') {
                            res.add(rightValue - leftValue);
                            
                        }
                        if (input.charAt(i) == '*') {
                            res.add(rightValue * leftValue);
                        }
                    }
                }
            }
        }
         //这里其实是一个base case，就是说当找不到符号时，说明input是一个数字，我们直接把数字加到返回集合里。这个数字可能是多位数，所以我们把这个base case放到找符号的loop后处理。
         if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
    
    private boolean isSymbol(char c) {
        return c - '0' < 0 || c - '0' > 9; 
    }
}

----------- 2022.3.26 同样思路 + memo --------------------------------------
/*
DC: 如果括号不嵌套，怎么放？会发现其实就是把符号左右分成两半，再连起来

pruning：
那么按照算法逻辑，按照运算符进行分割，一定存在下面两种分割情况：

(1 + 1) + (1 + 1 + 1)
(1 + 1 + 1) + (1 + 1)
算法会依次递归每一种情况，其实就是冗余计算嘛，所以我们可以对解法代码稍作修改，加一个备忘录来避免这种重复计算

Time:
当于在求算式 input 的所有合法括号组合吗. 
Estimated time complexity will be O(N*2^N) but the actual time complexity O(4^n / sqrt{n}) is bounded by the Catalan number and is beyond the scope of a coding interview.

Space: 
The space complexity of this algorithm will also be exponential, estimated at O(2^N) though the actual will be  O(4^n / sqrt{n}) 
*/

class Solution {
    Map<String, List<Integer>> memo = new HashMap<String, List<Integer>>(); 
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> results = new ArrayList<Integer>();
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }
        for (int i = 0; i < expression.length(); i++) {
            if (!Character.isDigit(expression.charAt(i))) {
                // 根据符号把s划分成两半
                List<Integer> first = diffWaysToCompute(expression.substring(0, i));
                List<Integer> second = diffWaysToCompute(expression.substring(i+1));
                // 把两半的结果接起来
                for (int a : first) {
                    for (int b : second) {
                        results.add(calculate(a, expression.charAt(i), b));
                    }
                }
            }
        }
        
        // base: 如果没能连起来，说明整个string就是个数字 
        if (results.isEmpty()) {
            results.add(Integer.parseInt(expression));
        }
        memo.put(expression, results);
        return results;
    }
    
    private int calculate(int first, char symbol, int second) {
        int val = 0; 
        if (symbol == '-') {
            val = first - second;
        }
        if (symbol == '+') {
            val = first + second;
        }
        if (symbol == '*') {
            val = first * second;
        }
        return val; 
    }
}    
    
