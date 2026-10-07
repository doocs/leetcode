class Solution {
public:
    int minReorder(int n, vector<vector<int>>& connections) {
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : connections) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b, 1);
            g[b].emplace_back(a, 0);
        }
        int ans = 0;
        vector<pair<int, int>> stk{{0, -1}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            int a = cur.first, fa = cur.second;
            for (auto& [b, c] : g[a]) {
                if (b != fa) {
                    ans += c;
                    stk.emplace_back(b, a);
                }
            }
        }
        return ans;
    }
};
