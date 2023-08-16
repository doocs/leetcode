class Solution {
public:
    bool robot(string command, vector<vector<int>>& obstacles, int x, int y) {
        set<pair<int, int>> vis;
        int i = 0, j = 0;
        vis.insert({i, j});
        for (char c : command) {
            if (c == 'U') {
                ++j;
            } else {
                ++i;
            }
            vis.insert({i, j});
        }
        int k = min(x / i, y / j);
        if (!vis.count({x - k * i, y - k * j})) {
            return false;
        }
        for (auto& ob : obstacles) {
            if (ob[0] > x || ob[1] > y) {
                continue;
            }
            k = min(ob[0] / i, ob[1] / j);
            if (vis.count({ob[0] - k * i, ob[1] - k * j})) {
                return false;
            }
        }
        return true;
    }
};