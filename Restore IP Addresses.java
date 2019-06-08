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

--------------------------------03.14.2019 update--------------------------------------------------------------
 /** Valid定义：IP地址总共有四段，每一段可能有一位，两位或者三位，范围是[0, 255] 并且大于两位时，01这种不合法
想法，我们用k来表示当前还需要分的段数，如果k = 0，则表示三个点已经加入完成，四段已经形成，若这时字符串刚好为空，则将当前分好的结果保存。若k != 0, 则对于每一段，我们分别用一位，两位，三位来尝试，分别判断其合不合法，如果合法，则调用递归继续分剩下的字符串，最终和求出所有合法组合
http://www.cnblogs.com/grandyang/p/4305572.html 

time:总共放n个隔板，每段可以有1，2，3位数三种选择，-> 0(3^n) 
**/
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        dfs(s, 4, "", res);
        return res; 
    }
    
    private void dfs(String s, int k, String address, List<String> res) {
        if (k == 0) {
            if (s.isEmpty()) {
                res.add(address);
            }
            // even though s is not empty, still need to return
            return;
        }

        // 分别对三种情况：一位数，两位数，三位数进行遍历
        for (int i = 1; i < 4; i++) {
            if (i > s.length()) {
                break;
            }
            String segment = s.substring(0, i);
            if (validString(segment)) {
                // we go to next bucket 
                dfs(s.substring(i), k-1, address + segment + (k==1 ? "" : "."), res);
            }
        }
    }
    
    
    // must between [0, 255] and if > 1 digits, the first letter cannot be 0. eg: 01 is not valid. 
    private boolean validString(String s) {
        // "010" -> 10 
        int n = Integer.parseInt(s);
        if (n < 0 || n > 255 || String.valueOf(n).length() != s.length()) {
            return false;
        }
        return true;
    }
}

---------------------判断能否组成valid IP-----------------------------------------
    
     private void dfs(String s, int k) {
        if (k == 0 && s.isEmpty()) {
            return true;
        }
        if (k < 0) {
            return false;
        }

        // 分别对三种情况：一位数，两位数，三位数进行遍历
        for (int i = 1; i < 4; i++) {
            if (i > s.length()) {
                break;
            }
            String segment = s.substring(0, i);
            if (validString(segment)) {
                // we go to next bucket 
                if (dfs(s.substring(i), k-1) {
                    return true;
                }
            }
        }
         return false;
    }
