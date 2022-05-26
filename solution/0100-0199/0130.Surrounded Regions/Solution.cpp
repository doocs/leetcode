class Solution {
private:
    void dfs(vector<vector<char>>& b, 
             vector<vector<bool>>& m, 
             int i, int j)
    {
        if (i < 0 || i >= b.size()
           || j < 0 || j >= b[0].size())
            return ;
            
        if (b[i][j] != 'O' || m[i][j])
            return ;
        
        m[i][j] = true ;
        dfs(b, m, i-1, j) ;
        dfs(b, m, i+1, j) ;
        dfs(b, m, i, j-1) ;
        dfs(b, m, i, j+1) ;
    }
public:
    void solve(vector<vector<char>>& board) {
        if (board.size() == 0 || board[0].size() == 0)
            return ;
        
        vector<vector<bool>> marks(board.size(), vector<bool>(board[0].size(), false)) ;
        const int w_1 = board[0].size() - 1 ; 
        for (int i = 0; i < w_1; ++i)
        {
            if (board[0][i] == 'O')
                dfs(board, marks, 0, i) ;
            if (board[board.size()-1][i] == 'O')
                dfs(board, marks, board.size()-1, i) ;
        }
        
        const int h_1 = board.size() - 1 ;
        for (int i = 1; i < h_1; ++i)
        {
            if (board[i][0] == 'O')
                dfs(board, marks, i, 0) ;
            if (board[i][board[0].size()-1] == 'O')
                dfs(board, marks, i, board[0].size()-1) ;
        }
        
        for (int i = 1; i < h_1; ++i)
            for (int j = 1; j < w_1; ++j)
                if (board[i][j] == 'O' && !marks[i][j])
                    board[i][j] = 'X' ;
                    
    }
};
