Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)



与Palindrome Partitioning I 结构一样，可作为升级版，有些地方“多一步”的需要特殊处理。 

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        List<String> list = new ArrayList<String>(); 
        
        if (s == null || s.length() == 0 ) {
            return result; 
        }
        helper(s, 0, list, result); 
        return result; 
        
    }
    
    private void helper(String s, int start, List<String> list, List<String> result){ 
        if (list.size() == 4){ //判断结束条件
            if (start == s.length()){
            
                StringBuilder element = new StringBuilder(); //多一步，本题需特殊处理的一个店，加上‘.’
                for(int j =0; j<list.size(); j++){
                    element.append(list.get(j));
                    element.append('.');
                }
                element.deleteCharAt(element.length()-1);
                result.add(element.toString()); 
            }
            
            else{
                return; 
            }
        }
    
        for (int i =start; i<s.length() && i <= start+3; i ++ ){ //多一步，本题特殊在每个substring长度最多不超过3。 
            String tmp = s.substring(start, i+1); //与 Palindrome Partitioning I 一样
            if(isValid(tmp)){
                list.add(tmp);
                helper(s,i+1 , list, result); 
                list.remove(list.size()-1); 
            }
        }
    }
    
    private boolean isValid(String tmp){ //判断函数
        if(tmp.charAt(0)=='0'){
            return (tmp.length() == 1);
        }
        return Integer.valueOf(tmp)>= 0 &&  Integer.valueOf(tmp)<= 255; 
    }
    
}
