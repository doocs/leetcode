class Solution {
public:
    int componentValue(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        int s = accumulate(nums.begin(), nums.end(), 0);
        int mx = *max_element(nums.begin(), nums.end());
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        auto check = [&](int t) -> bool {
            vector<int> sz(n);
            vector<array<int, 3>> stk{{0, -1, 0}};
            while (!stk.empty()) {
                auto [i, fa, state] = stk.back();
                stk.pop_back();
                if (state == 0) {
                    stk.push_back({i, fa, 1});
                    for (int j : g[i]) {
                        if (j != fa) {
                            stk.push_back({j, i, 0});
                        }
                    }
                } else {
                    int x = nums[i];
                    for (int j : g[i]) {
                        if (j != fa) {
                            x += sz[j];
                        }
                    }
                    if (x > t) {
                        return false;
                    }
                    sz[i] = x == t ? 0 : x;
                }
            }
            return sz[0] == 0;
        };
        for (int k = min(n, s / mx); k > 1; --k) {
            if (s % k == 0 && check(s / k)) {
                return k - 1;
            }
        }
        return 0;
    }
};
