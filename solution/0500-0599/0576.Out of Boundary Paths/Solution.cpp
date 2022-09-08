class Solution {
public:
    int m;
    int n;
    const int mod = 1e9 + 7;
    int f[51][51][51];
    int dirs[5] = {-1, 0, 1, 0, -1};

    int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        memset(f, 0xff, sizeof(f));
        this->m = m;
        this->n = n;
        return dfs(startRow, startColumn, maxMove);
    }

    int dfs(int i, int j, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 1;
        if (f[i][j][k] != -1) return f[i][j][k];
        if (k == 0) return 0;
        int res = 0;
        for (int t = 0; t < 4; ++t) {
            int x = i + dirs[t], y = j + dirs[t + 1];
            res += dfs(x, y, k - 1);
            res %= mod;
        }
        f[i][j][k] = res;
        return res;
    }
};