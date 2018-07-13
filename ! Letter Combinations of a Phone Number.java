Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> allsets = new ArrayList<String>();
        StringBuilder curset = new StringBuilder(); 
        
        if(digits == null || digits.equals("")){    // 极端条件一般这两种，for array就是null和size ==0 
            return allsets;
        }
        
        Map<Character, char[]> mapping = new HashMap<Character, char[]>(); 
        mapping.put('0',new char[] {}); 
        mapping.put('1',new char[] {});
        mapping.put('2',new char[] {'a','b','c'});
        mapping.put('3',new char[] {'d','e','f'});
        mapping.put('4',new char[] {'g','h','i'});
        mapping.put('5',new char[] {'j','k','l'});
        mapping.put('6',new char[] {'m','n','o'});
        mapping.put('7',new char[] {'p','q','r','s'});
        mapping.put('8',new char[] {'t','u','v'});
        mapping.put('9',new char[] {'w','x','y','z'});
    
        map(digits, mapping, curset, allsets);
        return allsets; 
    }
    
    private void map( String digits, Map<Character, char[]> mapping, StringBuilder curset,  List<String> allsets ){
        if(curset.length() == digits.length()){
            allsets.add(curset.toString()); 
            return;
        }
    
        for (char c : mapping.get(digits.charAt(curset.length()))){  //important！这题中每层的循环都不一样，巧妙运用curset.length() 来达到每层的循环。 
            curset.append(c); 
            map( digits, mapping, curset, allsets );
            curset.deleteCharAt(curset.length() -1) ;
        }    
    }
        
}

--------------------------- 07.12.18 真正搞清楚每层layer的递归
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<String>();
        // TODO: check if only contains 2-9
        if (digits == null || digits.length() == 0) {
            return results;
        }
        // Creating a map mapping from the number to letters 
        Map<Character, String> numToLetters = new HashMap<Character, String>();
        numToLetters.put('2', "abc");
        numToLetters.put('3', "def");
        numToLetters.put('4', "ghi");
        numToLetters.put('5', "jkl");
        numToLetters.put('6', "mno");
        numToLetters.put('7', "pqrs");
        numToLetters.put('8', "tuv");
        numToLetters.put('9', "wxyz");
        
        StringBuilder curResult = new StringBuilder();
        
        helper(digits, 0, curResult, results, numToLetters);
        return results;
    }
    private void helper(
        String digits, 
        int layer, 
        StringBuilder curResult, 
        List<String> results, 
        Map<Character, String> numToLetters) 
    {
        if (layer == digits.length()) {
                results.add(curResult.toString());
             return;
        }
        // In each layer, iterate from the first element. Here the change is layer! 
        for (int i = 0; i < numToLetters.get(digits.charAt(layer)).length(); i++ ){
                curResult.append(numToLetters.get(digits.charAt(layer)).charAt(i));
                helper(digits, layer + 1, curResult, results, numToLetters);
                curResult.deleteCharAt(curResult.length() -1);
        }
    } 
}
