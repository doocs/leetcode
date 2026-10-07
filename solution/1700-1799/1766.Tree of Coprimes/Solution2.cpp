class Solution {
public:
    vector<int> getCoprimes(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        vector<vector<int>> g(n);
        vector<vector<int>> f(51);
        vector<vector<pair<int, int>>> stks(51);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].emplace_back(v);
            g[v].emplace_back(u);
        }
        for (int i = 1; i < 51; ++i) {
            for (int j = 1; j < 51; ++j) {
                if (__gcd(i, j) == 1) {
                    f[i].emplace_back(j);
                }
            }
        }
        vector<int> ans(n);
        vector<array<int, 4>> stk{{0, -1, 0, 0}};
        while (!stk.empty()) {
            auto& cur = stk.back();
            int i = cur[0], fa = cur[1], depth = cur[2], k = cur[3];
            if (k == 0) {
                int t = -1, mx = -1;
                for (int v : f[nums[i]]) {
                    auto& s = stks[v];
                    if (!s.empty() && s.back().second > mx) {
                        t = s.back().first;
                        mx = s.back().second;
                    }
                }
                ans[i] = t;
            } else {
                int jprev = g[i][k - 1];
                if (jprev != fa) {
                    stks[nums[i]].pop_back();
                }
            }
            while (k < (int) g[i].size() && g[i][k] == fa) {
                ++k;
            }
            if (k == (int) g[i].size()) {
                stk.pop_back();
                continue;
            }
            int j = g[i][k];
            cur[3] = k + 1;
            stks[nums[i]].emplace_back(i, depth);
            stk.push_back({j, i, depth + 1, 0});
        }
        return ans;
    }
};
