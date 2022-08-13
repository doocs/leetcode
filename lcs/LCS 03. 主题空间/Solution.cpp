class Solution {
public:
    vector<int> p;

    int largestArea(vector<string>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<int> size(m * n + 1, 1);
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || grid[i][j] == '0')
                    p[find(i * n + j)] = find(m * n);
                else {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if ((grid[x][y] == '0' || grid[i][j] == grid[x][y]) && find(x * n + y) != find(i * n + j)) {
                            size[find(x * n + y)] += size[find(i * n + j)];
                            p[find(i * n + j)] = find(x * n + y);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (find(i * n + j) != find(m * n))
                    ans = max(ans, size[i * n + j]);
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};