class Solution {
public:
    vector<vector<int>> colorGrid(int n, int m, vector<vector<int>>& sources) {
        vector<vector<int>> ans(n, vector<int>(m, 0));
        vector<array<int, 3>> q;
        int dirs[] = {-1, 0, 1, 0, -1};
        for (auto& s : sources) {
            ans[s[0]][s[1]] = s[2];
            q.push_back({s[0], s[1], s[2]});
        }
        while (!q.empty()) {
            unordered_map<long long, int> vis;
            for (auto& curr : q) {
                int r = curr[0], c = curr[1], color = curr[2];
                for (int i = 0; i < 4; i++) {
                    int x = r + dirs[i], y = c + dirs[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < m && ans[x][y] == 0) {
                        long long key = (long long) x * m + y;
                        if (color > vis[key]) {
                            vis[key] = color;
                        }
                    }
                }
            }
            q.clear();
            for (auto const& [key, color] : vis) {
                int x = key / m;
                int y = key % m;
                ans[x][y] = color;
                q.push_back({x, y, color});
            }
        }
        return ans;
    }
};
