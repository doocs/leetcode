class Solution {
public:
    int maximumInvitations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        bool vis[210];
        int match[210];
        memset(match, -1, sizeof match);
        int ans = 0;
        function<bool(int)> find = [&](int i) -> bool {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && !vis[j]) {
                    vis[j] = true;
                    if (match[j] == -1 || find(match[j])) {
                        match[j] = i;
                        return true;
                    }
                }
            }
            return false;
        };
        for (int i = 0; i < m; ++i) {
            memset(vis, 0, sizeof vis);
            ans += find(i);
        }
        return ans;
    }
};