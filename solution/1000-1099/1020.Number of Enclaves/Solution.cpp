class Solution {
public:
    vector<int> p;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == 1)
                {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) p[find(i * n + j)] = find(m * n);
                    else
                    {
                        for (auto e : dirs)
                        {
                            if (grid[i + e[0]][j + e[1]] == 1) p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == 1 && find(i * n + j) != find(m * n)) ++res;
            }
        }
        return res;
    }
    
    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};