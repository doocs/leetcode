class Solution {
public:
    vector<int> p;
    int n;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int swimInWater(vector<vector<int>> &grid) {
        n = grid.size();
        for (int i = 0; i < n * n; ++i)
            p.push_back(i);
        vector<int> hi(n * n, 0);
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                hi[grid[i][j]] = index(i, j);
        for (int h = 0; h < n * n; ++h)
        {
            int x = hi[h] / n, y = hi[h] % n;
            for (auto dir : dirs)
            {
                int x1 = x + dir[0], y1 = y + dir[1];
                if (check(x1, y1) && grid[x1][y1] <= h)
                    p[find(index(x1, y1))] = find(hi[h]);
                if (find(0) == find(n * n - 1))
                    return h;
            }
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }

    int index(int i, int j) {
        return i * n + j;
    }

    bool check(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
};