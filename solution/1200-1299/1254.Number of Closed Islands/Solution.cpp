class Solution {
public:
    vector<int> p;

    int closedIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < m * n; ++i) p[i] = i;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) continue;
                int idx = i * n + j;
                if (i < m - 1 && grid[i + 1][j] == 0) p[find(idx)] = find((i + 1) * n + j);
                if (j < n - 1 && grid[i][j + 1] == 0) p[find(idx)] = find(i * n + j + 1);
            }
        }
        vector<bool> s(m * n, false);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) s[find(i * n + j)] = true;
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (!s[root]) continue;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) s[root] = false;
            }
        }
        int res = 0;
        for (auto e : s) {
            if (e) ++res;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};