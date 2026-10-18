class Solution {
public:
    vector<int> lastVisitedCell(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int r = 0, c = 0;
        vector<vector<bool>> vis(m, vector<bool>(n));
        while (true) {
            vector<int> cur = {r, c};
            vis[r][c] = true;
            switch (grid[r][c]) {
            case 'L': c--; break;
            case 'R': c++; break;
            case 'U': r--; break;
            case 'D': r++; break;
            }
            if (r >= 0 && r < m && c >= 0 && c < n && !vis[r][c]) {
                continue;
            }
            return cur;
        }
    }
};