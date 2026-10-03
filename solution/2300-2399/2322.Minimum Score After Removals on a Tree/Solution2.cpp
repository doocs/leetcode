class Solution {
public:
    int minimumScore(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        vector<vector<int>> g(n);
        for (const auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int s = 0;
        for (int x : nums) {
            s ^= x;
        }
        int ans = INT_MAX;
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                int s1 = componentXor(nums, g, i, j);
                ans = min(ans, collect(nums, g, i, j, s, s1));
            }
        }
        return ans;
    }

private:
    int componentXor(vector<int>& nums, vector<vector<int>>& g, int root, int ban) {
        int n = nums.size();
        vector<int> sub(n);
        vector<array<int, 3>> stk{{root, ban, 0}};
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
                int res = nums[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        res ^= sub[j];
                    }
                }
                sub[i] = res;
            }
        }
        return sub[root];
    }

    int collect(vector<int>& nums, vector<vector<int>>& g, int root, int ban, int s, int s1) {
        int n = nums.size();
        int ans = INT_MAX;
        vector<int> sub(n);
        vector<array<int, 3>> stk{{root, ban, 0}};
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
                int res = nums[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        int s2 = sub[j];
                        res ^= s2;
                        int mx = max({s ^ s1, s2, s1 ^ s2});
                        int mn = min({s ^ s1, s2, s1 ^ s2});
                        ans = min(ans, mx - mn);
                    }
                }
                sub[i] = res;
            }
        }
        return ans;
    }
};
