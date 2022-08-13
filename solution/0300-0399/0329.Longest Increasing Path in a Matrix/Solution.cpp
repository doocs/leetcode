class Solution {
public:
    vector<vector<int>> memo;
    vector<vector<int>> matrix;
    int m;
    int n;

    int longestIncreasingPath(vector<vector<int>>& matrix) {
        m = matrix.size();
        n = matrix[0].size();
        memo.resize(m, vector<int>(n, -1));
        this->matrix = matrix;
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = max(ans, dfs(i, j));
        return ans;
    }

    int dfs(int i, int j) {
        if (memo[i][j] != -1) return memo[i][j];
        int ans = 1;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j])
                ans = max(ans, dfs(x, y) + 1);
        }
        memo[i][j] = ans;
        return ans;
    }
};