
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
