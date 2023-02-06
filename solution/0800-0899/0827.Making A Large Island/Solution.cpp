class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int largestIsland(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> p(n * n);
        vector<int> size(n * n, 1);
        iota(p.begin(), p.end(), 0);

        function<int(int)> find;
        find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };

        int ans = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y]) {
                            int pa = find(x * n + y), pb = find(i * n + j);
                            if (pa == pb) continue;
                            p[pa] = pb;
                            size[pb] += size[pa];
                            ans = max(ans, size[pb]);
                        }
                    }
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
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y]) {
                            int root = find(x * n + y);
                            if (!vis.count(root)) {
                                vis.insert(root);
                                t += size[root];
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