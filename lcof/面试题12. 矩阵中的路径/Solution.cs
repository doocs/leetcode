public class Solution {
    public bool Exist(char[][] board, string word) {
        int k = 0;
        for (int i = 0; i < board.Length; i++)
        {
            for (int j = 0; j < board[0].Length; j++)
            {
                if (dfs(board, word, i, j, k)) {
                    return true;
                }   
            }
        }
        return false;
    }

    public bool dfs(char[][] board, string word, int i, int j, int k) {
        if (i > board.Length - 1 || i < 0 || j > board[0].Length - 1 || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        if (k == word.Length - 1) {
            return true;
        }

        board[i][j] = '\0';
        bool res = dfs(board, word, i+1, j, k+1) || dfs(board, word, i, j+1, k+1) || dfs(board, word, i-1, j, k+1) || dfs(board, word, i, j-1, k+1);
        board[i][j] = word[k];
        return res;
    }
}