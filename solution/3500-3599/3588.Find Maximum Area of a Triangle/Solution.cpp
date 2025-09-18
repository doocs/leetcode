class Solution {
public:
    long long maxArea(vector<vector<int>>& coords) {
        auto calc = [&]() -> long long {
            int mn = INT_MAX, mx = 0;
            unordered_map<int, int> f, g;
            for (auto& c : coords) {
                int x = c[0], y = c[1];
                mn = min(mn, x);
                mx = max(mx, x);
                if (f.count(x)) {
                    f[x] = min(f[x], y);
                    g[x] = max(g[x], y);
                } else {
                    f[x] = y;
                    g[x] = y;
                }
            }
            long long ans = 0;
            for (auto& [x, y] : f) {
                int d = g[x] - y;
                ans = max(ans, 1LL * d * max(mx - x, x - mn));
            }
            return ans;
        };

        long long ans = calc();
        for (auto& c : coords) {
            swap(c[0], c[1]);
        }
        ans = max(ans, calc());
        return ans > 0 ? ans : -1;
    }
};
