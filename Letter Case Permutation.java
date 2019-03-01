
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.


-----------------------------DFS--------------------------

class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        helper(S, 0, builder, result);
        return result;
        
    }
    
    private void helper(String S, int index, StringBuilder builder, List<String> result) {
        if (builder.length() == S.length()) {
            result.add(builder.toString());
            return;
        }
        
            if (Character.isDigit(S.charAt(index))) {
                builder.append(S.charAt(index));
                helper(S, index+1, builder, result);
                builder.deleteCharAt(builder.length() - 1);
            }
            else {
                builder.append(Character.toLowerCase(S.charAt(index)));
                helper(S, index+1, builder, result);
                builder.deleteCharAt(builder.length() - 1);
                
                builder.append(Character.toUpperCase(S.charAt(index)));
                helper(S, index+1, builder, result);
                builder.deleteCharAt(builder.length() - 1);
            }
   
    }
    
}

-------------------------------------BFS----------------------------------------------
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<String>();
        if (S == null || S.length() == 0) {
            result.add("");
            return result; 
        }
  
        Queue<String> queue = new LinkedList<String>();
        
        for (char c : S.toCharArray()) {
            if (queue.size() == 0) {
                queue.offer("");  
            }
           
            int size = queue.size();
            for (int i = 0; i < size; i++) {
               String cur = queue.poll();
                if (Character.isDigit(c)) {
                    queue.offer(cur + c);                    
                }
                else {
                    queue.offer(cur + Character.toLowerCase(c));
                    queue.offer(cur + Character.toUpperCase(c));                   
                }
            } 
        }
        while (queue.size() > 0) {
            result.add(queue.poll());
        }
        return result;   
    }

    
}
