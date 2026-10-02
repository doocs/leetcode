class Solution {
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> outdegree(m, vector<int>(n));
        vector<vector<int>> length(m, vector<int>(n, 1));
        queue<pair<int, int>> q;
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                        ++outdegree[i][j];
                    }
                }
                if (outdegree[i][j] == 0) q.emplace(i, j);
            }
        }

        int ans = 1;
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            ans = max(ans, length[i][j]);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                    length[x][y] = max(length[x][y], length[i][j] + 1);
                    if (--outdegree[x][y] == 0) q.emplace(x, y);
                }
            }
        }
        return ans;
    }
};
