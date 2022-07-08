class Solution {
public:
    const int mod = 1e9 + 7;
    vector<vector<vector<int>>> f;
    vector<vector<int>> s;
    int m;
    int n;

    int ways(vector<string>& pizza, int k) {
        m = pizza.size();
        n = pizza[0].size();
        s.assign(m + 1, vector<int>(n + 1, 0));
        f.assign(m, vector<vector<int>>(n, vector<int>(k, -1)));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + (pizza[i][j] == 'A');
        return dfs(0, 0, k - 1);
    }

    int dfs(int i, int j, int k) {
        if (f[i][j][k] != -1) return f[i][j][k];
        if (k == 0) return s[m][n] - s[m][j] - s[i][n] + s[i][j] > 0;
        int res = 0;
        for (int x = i + 1; x < m; ++x)
            if (s[x][n] - s[x][j] - s[i][n] + s[i][j])
                res = (res + dfs(x, j, k - 1)) % mod;
        for (int y = j + 1; y < n; ++y)
            if (s[m][y] - s[m][j] - s[i][y] + s[i][j])
                res = (res + dfs(i, y, k - 1)) % mod;
        f[i][j][k] = res;
        return res;
    }
};