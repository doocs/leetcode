class Solution {
public:
    vector<int> p;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int largestArea(vector<string>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || grid[i][j] == '0') 
                    p[find(i * n + j)] = find(m * n);
                else
                {
                    for (auto e : dirs)
                    {
                        if (grid[i + e[0]][j + e[1]] == '0' || grid[i][j]== grid[i + e[0]][j + e[1]])
                            p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                    }
                }
            }
        }
        unordered_map<int, int> mp;
        int res = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                int root = find(i * n + j);
                if (root != find(m * n))
                {
                    ++mp[root];
                    res = max(res, mp[root]);
                }
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};