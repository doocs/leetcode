class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        for (int i = 0; i < board.size(); ++i)
            for (int j = 0; j < board[0].size(); ++j)
                if (dfs(i, j, 0, board, word))
                    return 1;
        return 0;
    }

    bool dfs(int i, int j, int k, vector<vector<char>>& board, string word) {
        if (k == word.size()) return 1;
        if (i < 0 || i >= board.size() || j < 0 || j >= board[0].size() || board[i][j] != word[k]) return 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        board[i][j] = ' ';
        bool ans = 0;
        for (int l = 0; l < 4; ++l)
            ans = ans || dfs(i + dirs[l], j + dirs[l + 1], k + 1, board, word);
        board[i][j] = word[k];
        return ans;
    }
};