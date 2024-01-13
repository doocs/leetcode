class Solution {
public:
    long long maxPoints(vector<vector<int>>& points) {
        using ll = long long;
        int n = points[0].size();
        vector<ll> f(n);
        const ll inf = 1e18;
        for (auto& p : points) {
            vector<ll> g(n);
            ll lmx = -inf, rmx = -inf;
            for (int j = 0; j < n; ++j) {
                lmx = max(lmx, f[j] + j);
                g[j] = max(g[j], p[j] + lmx - j);
            }
            for (int j = n - 1; ~j; --j) {
                rmx = max(rmx, f[j] - j);
                g[j] = max(g[j], p[j] + rmx + j);
            }
            f = move(g);
        }
        return *max_element(f.begin(), f.end());
    }
};