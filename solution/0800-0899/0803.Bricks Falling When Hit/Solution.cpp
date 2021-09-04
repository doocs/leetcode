class Solution {
public:
    vector<int> p;
    vector<int> size;
    vector<vector<int>> g;
    int m;
    int n;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    vector<int> hitBricks(vector<vector<int>>& grid, vector<vector<int>>& hits) {
        m = grid.size();
        n = grid[0].size();
        for (int i = 0; i < m * n + 1; ++i)
        {
            p.push_back(i);
            size.push_back(1);
        }
        g = grid;
        for (auto e : hits)
            g[e[0]][e[1]] = 0;
        for (int j = 0; j < n; ++j)
            if (g[0][j] == 1)
                merge(j, m * n);
        for (int i = 1; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (g[i][j] == 0) continue;
                if (g[i - 1][j] == 1) merge(i * n + j, (i - 1) * n + j);
                if (j > 0 && g[i][j - 1] == 1) merge(i * n + j, i * n + j - 1);
            }
        }
        vector<int> res(hits.size());
        for (int k = hits.size() - 1; k >= 0; --k)
        {
            int i = hits[k][0], j = hits[k][1];
            if (grid[i][j] == 0) continue;
            int origin = size[find(m * n)];
            if (i == 0)
                merge(j, m * n);
            for (auto dir : dirs)
                if (check(i + dir[0], j + dir[1]))
                    merge(i * n + j, ((i + dir[0]) * n + j + dir[1]));
            int cur = size[find(m * n)];
            res[k] = max(0, cur - origin - 1);
            g[i][j] = 1;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb)
        {
            size[pb] += size[pa];
            p[pa] = pb;
        }
    }

    bool check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && g[i][j] == 1;
    }
};