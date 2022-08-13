class Solution {
public:
    vector<int> p;
    vector<int> size;

    vector<int> hitBricks(vector<vector<int>>& grid, vector<vector<int>>& hits) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n + 1);
        size.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) {
            p[i] = i;
            size[i] = 1;
        }
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                g[i][j] = grid[i][j];
        for (auto& h : hits) g[h[0]][h[1]] = 0;
        for (int j = 0; j < n; ++j)
            if (g[0][j] == 1)
                merge(j, m * n);
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] == 0) continue;
                if (g[i - 1][j] == 1) merge(i * n + j, (i - 1) * n + j);
                if (j > 0 && g[i][j - 1] == 1) merge(i * n + j, i * n + j - 1);
            }
        }
        vector<int> ans(hits.size());
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = hits.size() - 1; k >= 0; --k) {
            int i = hits[k][0], j = hits[k][1];
            if (grid[i][j] == 0) continue;
            g[i][j] = 1;
            int prev = size[find(m * n)];
            if (i == 0) merge(j, m * n);
            for (int l = 0; l < 4; ++l) {
                int x = i + dirs[l], y = j + dirs[l + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 1)
                    merge(i * n + j, x * n + y);
            }
            int curr = size[find(m * n)];
            ans[k] = max(0, curr - prev - 1);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            p[pa] = pb;
        }
    }
};