class Solution {
public:
    int numSubmat(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            vector<array<int, 3>> stk;
            for (int i = 0; i < m; ++i) {
                int cur = g[i][j];
                while (!stk.empty() && stk.back()[0] >= cur) {
                    stk.pop_back();
                }
                int cnt = cur * (i + 1);
                if (!stk.empty()) {
                    cnt = stk.back()[2] + cur * (i - stk.back()[1]);
                }
                ans += cnt;
                stk.push_back({cur, i, cnt});
            }
        }
        return ans;
    }
};
