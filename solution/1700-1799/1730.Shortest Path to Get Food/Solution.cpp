typedef pair<int, int> pii;

class Solution {
public:
    int getFood(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        queue<pii> q {{pos(grid)}};
        int ans = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                pii p = q.front();
                q.pop();
                for (int j = 0; j < 4; ++j) {
                    int x = p.first + dirs[j];
                    int y = p.second + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (grid[x][y] == '#') return ans;
                        if (grid[x][y] == 'O') {
                            grid[x][y] = 'X';
                            q.push({x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }

    pii pos(vector<vector<char>>& grid) {
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                if (grid[i][j] == '*')
                    return {i, j};
        return {};
    }
};