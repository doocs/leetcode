class Solution {
public:
    vector<int> p;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    
    vector<int> numIslands2(int m, int n, vector<vector<int>>& positions) {
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> grid(m, vector<int>(n, 0));
        vector<int> res;
        int cur = 0;
        for (auto position : positions)
        {
            int i = position[0], j = position[1];
            if (grid[i][j] == 1)
            {
                res.push_back(cur);
                continue;
            }
            grid[i][j] = 1;
            ++cur;
            for (auto e : dirs) {
                if (check(i + e[0], j + e[1], grid) && find(i * n + j) != find((i + e[0]) * n + j + e[1]))
                {
                    p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                    --cur;
                }
            }
            res.push_back(cur);
        }
        return res;
    }

    bool check(int i, int j, vector<vector<int>>& grid) {
        return i >= 0 && i < grid.size() && j >= 0 && j < grid[0].size() && grid[i][j] == 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};