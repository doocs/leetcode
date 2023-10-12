class Solution {
public:
    vector<int> mostSimilar(int n, vector<vector<int>>& roads, vector<string>& names, vector<string>& targetPath) {
        vector<int> g[n];
        for (auto& r : roads) {
            int a = r[0], b = r[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int m = targetPath.size();
        int f[m][n];
        int pre[m][n];
        memset(f, 0x3f, sizeof(f));
        memset(pre, -1, sizeof(pre));
        for (int j = 0; j < n; ++j) {
            f[0][j] = targetPath[0] != names[j];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k : g[j]) {
                    int t = f[i - 1][k] + (targetPath[i] != names[j]);
                    if (t < f[i][j]) {
                        f[i][j] = t;
                        pre[i][j] = k;
                    }
                }
            }
        }
        int k = 0;
        int mi = 1 << 30;
        for (int j = 0; j < n; ++j) {
            if (f[m - 1][j] < mi) {
                mi = f[m - 1][j];
                k = j;
            }
        }
        vector<int> ans(m);
        for (int i = m - 1; ~i; --i) {
            ans[i] = k;
            k = pre[i][k];
        }
        return ans;
    }
};