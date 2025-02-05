class Solution {
public:
    int maxCompatibilitySum(vector<vector<int>>& students, vector<vector<int>>& mentors) {
        int m = students.size();
        int n = students[0].size();
        vector<vector<int>> g(m, vector<int>(m));
        vector<bool> vis(m);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[i][j] += students[i][k] == mentors[j][k];
                }
            }
        }
        int ans = 0;
        auto dfs = [&](this auto&& dfs, int i, int s) {
            if (i >= m) {
                ans = max(ans, s);
                return;
            }
            for (int j = 0; j < m; ++j) {
                if (!vis[j]) {
                    vis[j] = true;
                    dfs(i + 1, s + g[i][j]);
                    vis[j] = false;
                }
            }
        };
        dfs(0, 0);
        return ans;
    }
};
