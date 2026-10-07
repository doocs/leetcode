class Solution {
public:
    int longestPath(vector<int>& parent, string s) {
        int n = parent.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; ++i) {
            g[parent[i]].push_back(i);
        }
        vector<int> down(n);
        int ans = 0;
        vector<array<int, 2>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, 1});
                for (int j : g[i]) {
                    stk.push_back({j, 0});
                }
            } else {
                int mx = 0;
                for (int j : g[i]) {
                    int x = down[j] + 1;
                    if (s[i] != s[j]) {
                        ans = max(ans, mx + x);
                        mx = max(mx, x);
                    }
                }
                down[i] = mx;
            }
        }
        return ans + 1;
    }
};
