class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int largestIsland(vector<vector<int>>& grid) {
        int n = grid.size();
        int ans = 0;
        int root = 0;
        vector<vector<int>> p(n, vector<int>(n));
        vector<int> cnt(n * n + 1);

        function<void(int, int)> dfs;
        dfs = [&](int i, int j) {
            p[i][j] = root;
            ++cnt[root];
            ans = max(ans, cnt[root]);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] && p[x][y] == 0) {
                    dfs(x, y);
                }
            }
        };

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && p[i][j] == 0) {
                    ++root;
                    dfs(i, j);
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    int t = 1;
                    unordered_set<int> vis;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            int root = p[x][y];
                            if (!vis.count(root)) {
                                vis.insert(root);
                                t += cnt[root];
                            }
                        }
                    }
                    ans = max(ans, t);
                }
            }
        }
        return ans;
    }
};