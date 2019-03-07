Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

-------------------------first thought--------------------------------
public class Solution {
    int length; 
    boolean ifright = false;  //用一个变量，改：直接dfs 设为boolean
    int row;
    int col; 
    
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length ==0 || board[0].length == 0 ){
            return false;
        }
         row = board.length;
         col = board[0].length; 
         
        if(word.equals(null)){
            return true; 
        }
        
        length = word.length() ;
        int curLoc = 0; 
        boolean[][] visited = new boolean[row][col];
        
        for(int i =0 ; i< row; i ++ ){
            for(int j =0; j<col ;j ++ ){
                if(board[i][j] == word.charAt(0)){
                   dfs(board, word, curLoc, i , j, visited);
                }
            }
        }
        if(ifright){
            return true;
        }
        return false; 
    }
    
    private void dfs(char[][] board, String word, int curLoc, int i, int j, boolean[][] visited){
        if(curLoc == length  ){ //这个条件要放在最前，eg[['a']]
             ifright = true; 
             return;
        }
        if(i <0 || i>= row || j <0 || j>= col){
            return; 
        }
        if(visited[i][j] == true){
            return; 
        }
 
        if(board[i][j] ==word.charAt(curLoc)){
            visited[i][j] =true;
            curLoc++;
            if(ifright == false ){
            dfs(board, word, curLoc, i-1, j,visited);  //改，可用loop代替重复写的四遍 dfs
            }
            if(ifright == false ){
            dfs(board, word, curLoc, i+1, j,visited); 
            }
            if(ifright == false ){
            dfs(board, word, curLoc, i, j-1,visited); 
            }
            if(ifright == false ){
            dfs(board, word, curLoc, i, j+1, visited);
            }
            visited[i][j] =false;
        }
    }
}

---------improve programming----------

public class Solution {
    int length; 
    int row;
    int col; 
    
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length ==0 || board[0].length == 0 ){
            return false;
        }
         row = board.length;
         col = board[0].length; 
         
        if(word.equals(null)){
            return true; 
        }
        
        length = word.length() ;
        int curLoc = 0; 
        boolean[][] visited = new boolean[row][col];
        
        for(int i =0 ; i< row; i ++ ){
            for(int j =0; j<col ;j ++ ){
                if(board[i][j] == word.charAt(0)){
                   if(dfs(board, word, curLoc, i , j, visited)){ // 如果dfs直接找到了路径，返回T，如果走完matrix仍没，则F
                       return true;
                   }
                }
            }
        }
        return false; 
    }
    
    private boolean dfs(char[][] board, String word, int curLoc, int x, int y, boolean[][] visited){
        if(curLoc == length ){ //or: curLoc == length -1 得把这个放到第一位，先判断。最后一个走完进这里return
             return true;
        }
        
        //if((x <0 || x>= row || y <0 || y >= col) || visited[x][y] ==true){ //或放到后面的if里，注意顺序得放在
                                                                            //board[x][y] ==word.charAt(curLoc) 之前
          //  return false; 
        //}
        
        if(  (x >=0 && x < row && y >=0 && y < col) && visited[x][y] ==false && board[x][y] ==word.charAt(curLoc) ){
            visited[x][y] = true; 
             curLoc++;
             int[] dx = {-1, 1, 0, 0}; //避免写四个递归的方法
	        int[] dy = {0, 0, -1, 1};
	        for(int d =0; d<4; d ++){ //新知识，学习
	            int i = x + dx[d];
	            int j = y +dy[d]; 
	                if( dfs(board, word, curLoc, i ,j, visited)){
	                    return true;
	                }
	            
	        }
	        visited[x][y] =false; //注意要回溯！ 
        }
	    return false;
	    
        
        之前的dfs写法： 
       /* if(curLoc == length ){ //or: curLoc == length -1 得把这个放到第一位，先判断。最后一个走完进这里return
             return true;
        }   
        if(i <0 || i>= row || j <0 || j>= col){
            return; 
        }
        if(visited[i][j] == true){
            return; 
        }*/
 
      /*  if(board[i][j] ==word.charAt(curLoc)){
            visited[i][j] =true;
            curLoc++;
            if(ifright == false ){ //每次递归前都要判断是否已可返回，避免重复劳动
            dfs(board, word, curLoc, i-1, j,visited); 
            }
            if(ifright == false ){
            dfs(board, word, curLoc, i+1, j,visited); 
            }
            if(ifright == false ){
            dfs(board, word, curLoc, i, j-1,visited); 
            }
            if(ifright == false ){
            dfs(board, word, curLoc, i, j+1, visited);
            }
            visited[i][j] =false; //记得要回溯
        }*/
         
----------------------------直接从这开始看---------------------------------------------
first backTracking solution 1: 
	    class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                    if (dfs(board, i, j, 0, word, visited)){
                        return true;
                    }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int row, int col, int index, String word, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
          
        //Order matters, must first check rows && cols in the range! otherwise the following condition used[row][col] == false will throw out of bond exception
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length && !visited[row][col] && board[row][col] == word.charAt(index)) {
            visited[row][col] = true; 
            int[] rowIndex = {1,-1,0,0};
            int[] colIndex = {0, 0, 1, -1};
            for (int i = 0; i < rowIndex.length; i++) {
                int newRow = row + rowIndex[i];
                int newCol = col + colIndex[i];
                if (dfs(board, newRow, newCol, index+1, word, visited)) {
                    return true;
                }    
            }
            visited[row][col] = false; 
        }
        return false; 
    }
}

----------------------------------------------------------------
second backtracking solution 2: 
	class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                    if (dfs(board, i, j, 0, word, visited)){
                        return true;
                    }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int row, int col, int index, String word, boolean[][] visited) {
     
        if (board[row][col] == word.charAt(index)) {
            if (index+1 == word.length()) {
            return true;
            }
            visited[row][col] = true;
            int[] rowIndex = {1,-1,0,0};
            int[] colIndex = {0, 0, 1, -1};
            for (int i = 0; i < rowIndex.length; i++) {
                int newRow = row + rowIndex[i];
                int newCol = col + colIndex[i];
                if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && !visited[newRow][newCol]) {
                    if (dfs(board, newRow, newCol, index+1, word, visited)) {
                        return true;
                    }
                }
            }
       			----this is the same as the for loop above ----- 
            // if (row-1>= 0 && !visited[row-1][col]) {
            //     if (dfs(board, row-1, col, index+1, word, visited)) {
            //         return true;
            //     }
            // }
            // if (row+1 < board.length && !visited[row+1][col]) {
            //     if (dfs(board, row +1, col, index+1, word, visited)) {
            //         return true;
            //     }
            // } 
            // if (col -1 >= 0 && !visited[row][col-1]) {
            //     if (dfs(board, row, col -1, index+1, word, visited)) {
            //         return true;
            //     }
            // }
            // if (col+1 < board[0].length && !visited[row][col+1]) {
            //     if (dfs(board, row, col +1, index+1, word, visited)) {
            //         return true;
            //     }
            // }
				
            visited[row][col] = false;
        }
        return false; 
        
    }
}
      
        
    

