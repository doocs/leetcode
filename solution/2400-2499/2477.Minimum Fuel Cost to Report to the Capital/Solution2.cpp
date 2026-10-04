class Solution {
public:
    long long minimumFuelCost(vector<vector<int>>& roads, int seats) {
        int n = roads.size() + 1;
        vector<vector<int>> g(n);
        for (auto& e : roads) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        long long ans = 0;
        vector<int> sz(n, 1);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            int a = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push_back({a, fa, 1});
                for (int b : g[a]) {
                    if (b != fa) {
                        stk.push_back({b, a, 0});
                    }
                }
            } else {
                for (int b : g[a]) {
                    if (b != fa) {
                        int t = sz[b];
                        ans += (t + seats - 1) / seats;
                        sz[a] += t;
                    }
                }
            }
        }
        return ans;
    }
};
