class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        vector<vector<int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        unordered_set<string> s;
        for (auto v : obstacles) s.insert(to_string(v[0]) + "." + to_string(v[1]));
        int ans = 0, p = 1;
        int x = 0, y = 0;
        for (int v : commands) {
            if (v == -2)
                p = (p + 3) % 4;
            else if (v == -1)
                p = (p + 1) % 4;
            else {
                while (v--) {
                    int nx = x + dirs[p][0], ny = y + dirs[p][1];
                    if (s.count(to_string(nx) + "." + to_string(ny))) break;
                    x = nx;
                    y = ny;
                    ans = max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }
};