class Solution {
public:
    int maxArea(vector<vector<int>>& mat) {
        return max(calc(mat), calc(transpose(mat)));
    }

private:
    int calc(const vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();

        vector<vector<int>> f(m + 1, vector<int>(n + 1));
        vector<int> g(m + 1), suf(m + 1);

        for (int i = m - 1; i > 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j]) {
                    f[i][j] = min({f[i + 1][j],
                                  f[i][j + 1],
                                  f[i + 1][j + 1]})
                        + 1;
                    g[i] = max(g[i], f[i][j]);
                }
            }
            suf[i] = max(suf[i + 1], g[i]);
        }

        f.assign(m + 1, vector<int>(n + 1));
        g.assign(m + 1, 0);
        vector<int> pre(m + 1);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (mat[i - 1][j - 1]) {
                    f[i][j] = min({f[i - 1][j],
                                  f[i][j - 1],
                                  f[i - 1][j - 1]})
                        + 1;
                    g[i] = max(g[i], f[i][j]);
                }
            }
            pre[i] = max(pre[i - 1], g[i]);
        }

        int ans = 0;
        for (int i = 1; i < m; i++) {
            int t = min(pre[i], suf[i]);
            ans = max(ans, t * t);
        }

        return ans;
    }

    vector<vector<int>> transpose(const vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();

        vector<vector<int>> ans(n, vector<int>(m));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][i] = mat[i][j];
            }
        }

        return ans;
    }
};