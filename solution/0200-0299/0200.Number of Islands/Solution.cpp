class Solution {
public:
    vector<int> p;

    int numIslands(vector<vector<char>> &grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                p[i * n + j] = grid[i][j] == '1' ? i * n + j : -1;
            }
        }
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == '1')
                {
                    if (i < m - 1 && grid[i + 1][j] == '1')
                    {
                        p[find(i * n + j)] = find((i + 1) * n + j);
                    }
                    if (j < n - 1 && grid[i][j + 1] == '1')
                    {
                        p[find(i * n + j)] = find(i * n + j + 1);
                    }
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (p[i * n + j] != -1 && i * n + j == find(i * n + j))
                    ++cnt;
            }
        }
        return cnt;
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};