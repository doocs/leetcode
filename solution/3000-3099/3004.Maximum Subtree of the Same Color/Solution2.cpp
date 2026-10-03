class Solution {
public:
    int maximumSubtreeSize(vector<vector<int>>& edges, vector<int>& colors) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> size(n, 1);
        vector<char> ok(n);
        int ans = 0;
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
                bool good = true;
                for (int b : g[a]) {
                    if (b != fa) {
                        good = good && colors[a] == colors[b] && ok[b];
                        size[a] += size[b];
                    }
                }
                if (good) {
                    ans = max(ans, size[a]);
                }
                ok[a] = good;
            }
        }
        return ans;
    }
};
