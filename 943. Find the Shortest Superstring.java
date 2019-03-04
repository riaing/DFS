Given an array A of strings, find any smallest string that contains each string in A as a substring.

We may assume that no string in A is substring of another string in A.

 
Example 1:

Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:

Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"
 

Note:

1 <= A.length <= 12
1 <= A[i].length <= 20
--------------------------------DFS 找到所有string的permutation, 找到superstring最短的一个。注意这里dfs存储的是string的index，而不是
在dfs中找到最短superstring（费时间）
class Solution {
    int bestLen = Integer.MAX_VALUE;
    int[] bestPathIndex; 
    int[][] shortestArray;
    public String shortestSuperstring(String[] A) {
        shortestArray = buildShortesLengthArray(A);
        int n = A.length;
        dfs(A, 0, new boolean[n], 0, new int[n]);
        String res = A[bestPathIndex[0]];
        for (int k = 1; k < bestPathIndex.length; k++) {
            int i = bestPathIndex[k-1];
            int j = bestPathIndex[k];
            res += A[j].substring(A[j].length() - shortestArray[i][j]);
        }
        return res;
    }
    //各个单词的string进行组合，save组合的string的index，
    private void dfs(String[] A, int index, boolean[] visited, int curLen, int[] pathIndex) {
        if (curLen >= bestLen) {
            return;
        }
        if (index == A.length) {
            bestPathIndex = Arrays.copyOf(pathIndex, pathIndex.length);
            bestLen = curLen; 
            return;
        }
        for (int i = 0; i< A.length; i++ ){
            if (visited[i] == true) {
                continue;
            }
            pathIndex[index] = i; //当前层填入第i个string;
            visited[i] = true; 
            int newCurLen = index == 0 ? A[i].length() : curLen + shortestArray[pathIndex[index-1]][i]; 
            dfs(A, index+1, visited, newCurLen, pathIndex);
            visited[i] = false;
        } 
    }
    // find the shortest cost of adding jth string behind ith string in String[] A 
    // eg: if ith string = ttca, jth string = catg, then return 2(tg).
    private int shortestLength(String[] A, int i, int j) {
        int res = A[j].length();
        for (int k = 0; k < Math.min(A[i].length(), A[j].length()); k++) {
            if (A[i].substring(A[i].length() - (k+1)).equals(A[j].substring(0, k+1))) {
                res = A[j].length() - (k+1);
            }
        }
        return res; 
    }
    
    private int[][] buildShortesLengthArray(String[] A) {
        int[][] res = new int[A.length][A.length];
        for (int i = 0; i< A.length; i++) {
            for (int j = 0; j< A.length; j++) {
                res[i][j] = shortestLength(A, i, j);
            }
        }
        return res; 
    }
}
