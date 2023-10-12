class Solution {
public:
    int minimumOperations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> match(m * n, -1);
        unordered_set<int> vis;
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i + j) % 2 && grid[i][j]) {
                    int x = i * n + j;
                    if (i < m - 1 && grid[i + 1][j]) {
                        g[x].push_back(x + n);
                    }
                    if (i && grid[i - 1][j]) {
                        g[x].push_back(x - n);
                    }
                    if (j < n - 1 && grid[i][j + 1]) {
                        g[x].push_back(x + 1);
                    }
                    if (j && grid[i][j - 1]) {
                        g[x].push_back(x - 1);
                    }
                }
            }
        }
        int ans = 0;
        function<int(int)> find = [&](int i) -> int {
            for (int& j : g[i]) {
                if (!vis.count(j)) {
                    vis.insert(j);
                    if (match[j] == -1 || find(match[j])) {
                        match[j] = i;
                        return 1;
                    }
                }
            }
            return 0;
        };
        for (auto& [i, _] : g) {
            ans += find(i);
            vis.clear();
        }
        return ans;
    }
};