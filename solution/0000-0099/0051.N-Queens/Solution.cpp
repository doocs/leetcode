class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> res;
        vector<string> g(n, string(n, '.'));
        vector<bool> col(n, false);
        vector<bool> dg(2 * n, false);
        vector<bool> udg(2 * n, false);
        dfs(0, n, col, dg, udg, g, res);
        return res;
    }

    void dfs(int u, int n, vector<bool>& col, vector<bool>& dg, vector<bool>& udg, vector<string>& g, vector<vector<string>>& res) {
        if (u == n) {
            res.push_back(g);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!col[i] && !dg[u + i] && !udg[n - u + i]) {
                g[u][i] = 'Q';
                col[i] = dg[u + i] = udg[n - u + i] = true;
                dfs(u + 1, n, col, dg, udg, g, res);
                g[u][i] = '.';
                col[i] = dg[u + i] = udg[n - u + i] = false;
            }
        }
    }
};