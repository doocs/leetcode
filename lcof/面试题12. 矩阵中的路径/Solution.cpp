class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> bool {
            if (k == word.size()) {
                return true;
            }
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[k]) {
                return false;
            }
            board[i][j] = '.';
            bool ans = 0;
            for (int l = 0; l < 4; ++l) {
                ans |= dfs(i + dirs[l], j + dirs[l + 1], k + 1);
            }
            board[i][j] = word[k];
            return ans;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
};