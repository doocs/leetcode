class Solution {
public:
    long long maxScore(vector<int>& a, vector<int>& b) {
        int m = a.size(), n = b.size();
        long long f[m][n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int j) -> long long {
            if (j >= n) {
                return i >= m ? 0 : LLONG_MIN / 2;
            }
            if (i >= m) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            return f[i][j] = max(dfs(dfs, i, j + 1), 1LL * a[i] * b[j] + dfs(dfs, i + 1, j + 1));
        };
        return dfs(dfs, 0, 0);
    }
};
