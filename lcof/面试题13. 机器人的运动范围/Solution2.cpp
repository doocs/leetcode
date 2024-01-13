class Solution {
public:
    int movingCount(int m, int n, int k) {
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        auto f = [](int x) {
            int s = 0;
            for (; x; x /= 10) {
                s += x % 10;
            }
            return s;
        };
        function<int(int i, int j)> dfs = [&](int i, int j) -> int {
            if (i < 0 || i >= m || j < 0 || j >= n || vis[i][j] || f(i) + f(j) > k) {
                return false;
            }
            vis[i][j] = true;
            return 1 + dfs(i + 1, j) + dfs(i, j + 1);
        };
        return dfs(0, 0);
    }
};