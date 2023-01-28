class Solution {
public:
    int stoneGameVII(vector<int>& stones) {
        int n = stones.size();
        int f[n][n];
        memset(f, 0, sizeof f);
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int a = s[j + 1] - s[i + 1] - dfs(i + 1, j);
            int b = s[j] - s[i] - dfs(i, j - 1);
            return f[i][j] = max(a, b);
        };
        return dfs(0, n - 1);
    }
};