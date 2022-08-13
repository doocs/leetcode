class Solution {
public:
    vector<int> p;

    bool containsCycle(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<int> dirs = {0, 1, 0};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 2; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x < m && y < n && grid[x][y] == grid[i][j]) {
                        if (find(x * n + y) == find(i * n + j)) return 1;
                        p[find(x * n + y)] = find(i * n + j);
                    }
                }
            }
        }
        return 0;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};