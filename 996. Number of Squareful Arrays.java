Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.

Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].

 

Example 1:

Input: [1,17,8]
Output: 2
Explanation: 
[1,8,17] and [17,8,1] are the valid permutations.
Example 2:

Input: [2,2,2]
Output: 1
 

Note:

1 <= A.length <= 12
0 <= A[i] <= 1e9
----------------------------------DFS,permutation all element. time (n!), space(n)--------------------------------------------------------------------------
//这个题的问题规模只有12个，也就是提醒我们可以使用O(N!)的算法，所以可以直接使用回溯法。
class Solution {
    // 注意! integer进入递归后并不会被更新，所以用一个global variable。 
    int res = 0;
    public int numSquarefulPerms(int[] A) {
        List<Integer> set = new ArrayList<Integer>(); 
        boolean[] used = new boolean[A.length];
        Arrays.sort(A); // will be easier to judge in later 
        if(A != null){
            helper(A, used, set); 
        }
        return res; 
    }
    
    // 判断是否squareful，看根号后mod的余数是否为零.注意mata.sqrt() -> double 
    private boolean squareful(int i, int j) {
        return Math.sqrt(i + j) % 1 == 0;
    }
    
    private void helper(int[] A, boolean[] used,  List<Integer> set){
        if(set.size() == A.length){
            res = res + 1;
            return;
        }
        for(int i = 0; i<A.length; i ++){    
            if(used[i] == true){
                continue;
            }
            // Avoid duplications.
            if(i!= 0 && A[i] == A[i-1] && used[i-1] == false ){ 
                continue; 
            }
            // Prune invalid solutions.
            //注意这里比较的是当前set的前一个元素和A中有可能的下一个元素，所以是set.last element vs. A[i] 
            if (!set.isEmpty() && !squareful(set.get(set.size()-1), A[i])) {
                continue; 
            }      
            set.add(A[i]);
            used[i] = true;
            helper(A, used, set);
            used[i] = false;
            set.remove(set.size()-1);
        }
    }
}

---------------------DFS construct a graph.  time(n^n), space(n)S pace Complexity of hashmap in big-O notation is O(n) where n is the number of entries. -----------------------------------------------------------------
 建立一个graph where every node is the element in A and edge vw such as node v + node w is squareful. DFS every node on 
 the graph to find all Hamitonian path starting from such node 
 Map<Integer, Integer> count: Key: every element in A; value: the occurance of such element (how many such node) 
 Map<Integer, List<Integer>> graph; the edge of key to its values, where key-value is squreful. 
 dfs(int start, int k): starting from node "start" and there are k nodes left to visit, find all paths of which such path 
 starts from node "start", and visited k other nodes. 
 
 
class Solution {
    // for every int in A, how many node exist 
    Map<Integer, Integer> count;
    // for every v in A, find all it's squreful w in A such as v+w is squareful 
    Map<Integer, List<Integer>> graph;
    
    private boolean squreful(int x, int y) {
        return Math.sqrt(x + y) % 1 == 0;
    }
    
    public int numSquarefulPerms(int[] A) {
        //construct the graph of node
        count = new HashMap<Integer, Integer>();
        graph = new HashMap<Integer, List<Integer>>();
        for (int x : A) {
            count.put(x, count.getOrDefault(x, 0) + 1);
        }
        
        // construct the graph of edge vw which v+w is squareful 
        for (int v : count.keySet()) {
            for (int w : count.keySet()) {
                if (!graph.containsKey(v)) {
                    graph.put(v, new ArrayList<>());
                }
                if (squreful(v,w)) {
                    graph.get(v).add(w);
                }
            }
        }
        
        // find all hamitonian path that start from each node in the graph 
        int res = 0;
        for (int x : count.keySet()) {
            res = res + dfs(x, A.length -1);
        }
        return res;       
    }
    
    // find all paths that start from node "start" to visit k nodes 
    private int dfs(int start, int k) {
        if (k == 0) {
            return 1; 
        }
        // mark such node as visited 
        count.put(start, count.get(start) -1);
        int res = 0; 
        for (int node : graph.get(start)) {
            // if the node is not visited, visit it 
            if (count.get(node) != 0) {
                res = res + dfs(node, k-1);
            }
        }
        count.put(start, count.get(start) + 1);
        return res; 
    }
}
