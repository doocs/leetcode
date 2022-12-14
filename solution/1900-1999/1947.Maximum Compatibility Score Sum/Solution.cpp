class Solution {
public:
    int maxCompatibilitySum(vector<vector<int>>& students, vector<vector<int>>& mentors) {
        int m = students.size();
        int n = students[0].size();
        int g[m][m];
        memset(g, 0, sizeof g);
        bool vis[m];
        memset(vis, 0, sizeof vis);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[i][j] += students[i][k] == mentors[j][k];
                }
            }
        }
        int ans = 0;
        function<void(int, int)> dfs = [&](int i, int t) {
            if (i == m) {
                ans = max(ans, t);
                return;
            }
            for (int j = 0; j < m; ++j) {
                if (!vis[j]) {
                    vis[j] = true;
                    dfs(i + 1, t + g[i][j]);
                    vis[j] = false;
                }
            }
        };
        dfs(0, 0);
        return ans;
    }
};