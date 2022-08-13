class Solution {
public:
    int nearestExit(vector<vector<char>>& maze, vector<int>& entrance) {
        int m = maze.size(), n = maze[0].size();
        queue<vector<int>> q {{entrance}};
        maze[entrance[0]][entrance[1]] = '+';
        int ans = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                auto p = q.front();
                q.pop();
                for (int l = 0; l < 4; ++l) {
                    int x = p[0] + dirs[l], y = p[1] + dirs[l + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == '.') {
                        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) return ans;
                        q.push({x, y});
                        maze[x][y] = '+';
                    }
                }
            }
        }
        return -1;
    }
};