public class Solution {
    int length; 
    boolean ifright = false; 
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
        if(curLoc == length  ){
            //-1 && board[i][j] ==word.charAt(curLoc)
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
            visited[i][j] =false;
        }
         
        
        
    }
}
