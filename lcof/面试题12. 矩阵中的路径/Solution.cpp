class Solution {
public:
    bool dfs(vector<vector<char>>& board, string& word, int cur, int x, int y) {
        if (board[x][y] != word[cur]) {
            return false;
        }

        if (cur == word.size()-1) {
            return true;
        }
        
        char t = board[x][y];
        board[x][y] = '*';    // 表示查询过了这个字段
        int dx[4] = {-1, 0, 1, 0};
        int dy[4] = {0, 1, 0, -1};
        for (int k = 0; k < 4; k++) {
            // 从上、右、下、左四个方向，开始dfs
            int a = x + dx[k], b = y + dy[k];
            if (a >= 0 && a < board.size() && b >= 0 && b < board[0].size()) {
                if (dfs(board, word, cur+1, a, b)) {
                    return true;
                }
            }
        }

        board[x][y] = t;
        return false;
    }

    bool exist(vector<vector<char>>& board, string word) {
        int x = board.size();
        int y = board[0].size();
        if (0 == x || 0 == y) {
            return false;
        } 

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }
};