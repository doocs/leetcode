class Solution {
public:
    int minKnightMoves(int x, int y) {
        x += 310;
        y += 310;
        int ans = 0;
        queue<pair<int, int>> q;
        q.push({310, 310});
        vector<vector<bool>> vis(700, vector<bool>(700));
        vis[310][310] = true;
        vector<vector<int>> dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        while (!q.empty()) {
            for (int k = q.size(); k > 0; --k) {
                auto p = q.front();
                q.pop();
                if (p.first == x && p.second == y) return ans;
                for (auto& dir : dirs) {
                    int c = p.first + dir[0], d = p.second + dir[1];
                    if (!vis[c][d]) {
                        vis[c][d] = true;
                        q.push({c, d});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};