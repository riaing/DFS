1、从边缘入手，因为和边缘上的‘O’相连的‘O’都不会变，所以找出所有这样的‘O’，剩下的‘O’就是被完全包围的，把它们全部变为‘X’。

2、DFS过程中，暂时把遍历过的‘O'用另外一个符号’#‘代替，最后把被包围的’O‘变成’X'后，在把‘#’恢复成‘O’。
注意，当行或列少于3时，不可能有被包围的‘O’。

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X


-------------------------DFS----------------------
public class Solution {
    int row, col; 
    
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
         row = board.length;
         col = board[0].length;
         if(row <3 || col < 3){
             return;
         }
        //重点，从四周入手，看有多少零不用变为x
        for(int j =0 ; j <col ; j++){ 
            if(board[0][j] =='O'){
                dfs(board, 0 , j);
            }
            if(board[row-1][j] =='O'){
                dfs( board, row-1 , j) ; 
            }
        }
        
        for (int i = 0; i < row; i ++){
            if( board[i][0] == 'O'){
                dfs(board, i , 0);
            }
            if( board[i][col -1] == 'O'){
                dfs(board, i , col-1);
            }
        }
        
        for(int i = 0; i <row; i++){
            for(int j =0; j< col;j++){
                if(board[i][j] == 'O'){//把中间没被四周0污染过的变为x
                    board[i][j] ='X';
                }
                if(board[i][j] == '2'){ //把四周的0们重新恢复为0
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int i, int j){
    //为避免overflow，其实我也不懂为什么要分开写if，可能就少进了一层吧。反正这个题懂得从四周入手就行了，反正DFS overflow
        if(board[i][j] =='O'){
            board[i][j] ='2';
            if(i > 1){ 
            dfs(board, i-1 , j);
            } 
            if(i<row-2){
            dfs(board, i+1 , j);
            }
            if(j<col -2){
            dfs(board, i , j +1);
            }
            if(j>1){
            dfs(board, i , j -1); 
            }
        }
    }
}
这个思路是从边缘向内部找连通的‘O’的，所以DFS时，就没必要在判断边缘上的字母了，因此DFS中，i 和 j 的条件为：
i > 1, i < row - 2; j > 1, j < col - 2。（为什么？如果是 i = 1 时，那么 dfs(board, i - 1, j) 就是判断 [0, j] 了，
而而边缘上的字母会被遍历判断的，这样就重复判断了，会导致栈溢出）。



------------------------BFS-----------------


public class Solution {
    int row, col; 
    
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
         row = board.length;
         col = board[0].length;
         if(row <3 || col < 3){
             return;
         }
        //重点，从四周入手，看有多少零不用变为x
        for(int j =0 ; j <col ; j++){ 
            if(board[0][j] =='O'){
                dfs(board, 0 , j);
            }
            if(board[row-1][j] =='O'){
                dfs( board, row-1 , j) ; 
            }
        }
        
        for (int i = 0; i < row; i ++){
            if( board[i][0] == 'O'){
                dfs(board, i , 0);
            }
            if( board[i][col -1] == 'O'){
                dfs(board, i , col-1);
            }
        }
        
        for(int i = 0; i <row; i++){
            for(int j =0; j< col;j++){
                if(board[i][j] == 'O'){//把中间没被四周0污染过的变为x
                    board[i][j] ='X';
                }
                if(board[i][j] == '2'){ //把四周的0们重新恢复为0
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int x, int y){
        board[x][y] ='2'; 
        Queue<Integer> q = new LinkedList<Integer>(); 
        int loc = x * col + y ; 
        q.offer(loc); 
        
        while(q.size() != 0 ){
            int m = q.poll();
            int i = m /col;
            int j = m %col; 
            
            if(i>0 && board[i -1][j] == 'O'){
                board[i -1][j] ='2';
                q.offer( (i-1)*col +j);
            }
            
              if( i< row-1 && board[i +1][j] == 'O'){
                board[i +1][j] ='2';
                q.offer( (i+1)*col +j);
            }
            
              if(j>0 && board[i][j -1] == 'O'){
                board[i][j -1] ='2';
                q.offer( i*col +j -1);
            }
            
              if(j< col-1 && board[i][j +1] == 'O'){
                board[i][j+1] ='2';
                q.offer( i*col +j+1);
            }
            
            
            
        }
        

    }
}
