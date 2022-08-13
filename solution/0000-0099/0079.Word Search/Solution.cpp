class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (dfs(i, j, 0, m, n, board, word))
                    return true;
        return false;
    }

    bool dfs(int i, int j, int cur, int m, int n, vector<vector<char>>& board, string& word) {
        if (cur == word.size()) return true;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[cur]) return false;
        char t = board[i][j];
        board[i][j] = '0';
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (dfs(x, y, cur + 1, m, n, board, word)) return true;
        }
        board[i][j] = t;
        return false;
    }
};