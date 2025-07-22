class Solution {
public:
    bool isEscapePossible(vector<vector<int>>& blocked, vector<int>& source, vector<int>& target) {
        const int n = 1e6;
        int m = blocked.size() * blocked.size() / 2;
        using ll = long long;
        unordered_set<ll> s;
        const int dirs[5] = {-1, 0, 1, 0, -1};
        auto f = [&](int i, int j) {
            return (ll) i * n + j;
        };
        for (const auto& b : blocked) {
            s.insert(f(b[0], b[1]));
        }
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        unordered_set<ll> vis1, vis2;
        auto dfs = [&](this auto&& dfs, int sx, int sy, int tx, int ty, unordered_set<ll>& vis) -> bool {
            vis.insert(f(sx, sy));
            if (vis.size() > m) {
                return true;
            }
            for (int k = 0; k < 4; ++k) {
                int x = sx + dirs[k], y = sy + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n) {
                    if (x == tx && y == ty) {
                        return true;
                    }
                    auto key = f(x, y);
                    if (!s.contains(key) && !vis.contains(key) && dfs(x, y, tx, ty, vis)) {
                        return true;
                    }
                }
            }
            return false;
        };
        return dfs(sx, sy, tx, ty, vis1) && dfs(tx, ty, sx, sy, vis2);
    }
};