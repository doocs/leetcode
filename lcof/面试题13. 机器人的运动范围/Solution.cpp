class Solution {
public:
    int movingCount(int m, int n, int k) {
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        int ans = 0;
        int dirs[3] = {1, 0, 1};
        auto f = [](int x) {
            int s = 0;
            for (; x; x /= 10) {
                s += x % 10;
            }
            return s;
        };
        function<void(int i, int j)> dfs = [&](int i, int j) {
            vis[i][j] = true;
            ++ans;
            for (int l = 0; l < 2; ++l) {
                int x = i + dirs[l], y = j + dirs[l + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && f(x) + f(y) <= k && !vis[x][y]) {
                    dfs(x, y);
                }
            }
        };
        dfs(0, 0);
        return ans;
    }
};