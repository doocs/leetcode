class Solution {
public:
    int m, n;
    const int mod = 1e9 + 7;
    vector<vector<int>> f;
    vector<vector<int>> c;

    int idealArrays(int n, int maxValue) {
        this->m = maxValue;
        this->n = n;
        f.assign(maxValue + 1, vector<int>(16, -1));
        c.assign(n, vector<int>(16, 0));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j <= i && j < 16; ++j)
                c[i][j] = !j ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % mod;
        int ans = 0;
        for (int i = 1; i <= m; ++i) ans = (ans + dfs(i, 1)) % mod;
        return ans;
    }

    int dfs(int i, int cnt) {
        if (f[i][cnt] != -1) return f[i][cnt];
        int res = c[n - 1][cnt - 1];
        if (cnt < n)
            for (int k = 2; k * i <= m; ++k)
                res = (res + dfs(k * i, cnt + 1)) % mod;
        f[i][cnt] = res;
        return res;
    }
};