class Solution {
public:
    int largestIsland(vector<vector<int>>& grid) {
        int n = grid.size();
        int p[n][n];
        memset(p, 0, sizeof(p));
        int cnt[n * n + 1];
        memset(cnt, 0, sizeof(cnt));
        const int dirs[5] = {-1, 0, 1, 0, -1};
        int root = 0;
        int ans = 0;
        function<int(int, int)> dfs = [&](int i, int j) {
            p[i][j] = root;
            ++cnt[root];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] && !p[x][y]) {
                    dfs(x, y);
                }
            }
            return cnt[root];
        };
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && !p[i][j]) {
                    ++root;
                    ans = max(ans, dfs(i, j));
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    unordered_set<int> s;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            s.insert(p[x][y]);
                        }
                    }
                    int t = 1;
                    for (int x : s) {
                        t += cnt[x];
                    }
                    ans = max(ans, t);
                }
            }
        }
        return ans;
    }
};