class Solution {
public:
    vector<int> minimumFlips(int n, vector<vector<int>>& edges, string start, string target) {
        vector<vector<pair<int, int>>> g(n);
        for (int i = 0; i < n - 1; ++i) {
            int a = edges[i][0], b = edges[i][1];
            g[a].push_back({b, i});
            g[b].push_back({a, i});
        }
        vector<int> ans;
        vector<char> need(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            int a = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push_back({a, fa, 1});
                for (auto [b, i] : g[a]) {
                    if (b != fa) {
                        stk.push_back({b, a, 0});
                    }
                }
            } else {
                bool rev = start[a] != target[a];
                for (auto [b, i] : g[a]) {
                    if (b != fa && need[b]) {
                        ans.push_back(i);
                        rev = !rev;
                    }
                }
                need[a] = rev;
            }
        }
        if (need[0]) {
            return {-1};
        }
        ranges::sort(ans);
        return ans;
    }
};
