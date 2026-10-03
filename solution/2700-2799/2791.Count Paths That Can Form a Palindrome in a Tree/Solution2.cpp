class Solution {
public:
    long long countPalindromePaths(vector<int>& parent, string s) {
        int n = parent.size();
        vector<vector<pair<int, int>>> g(n);
        for (int i = 1; i < n; ++i) {
            g[parent[i]].emplace_back(i, 1 << (s[i] - 'a'));
        }
        unordered_map<int, int> cnt;
        cnt[0] = 1;
        long long ans = 0;
        vector<pair<int, int>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, xo] = stk.back();
            stk.pop_back();
            for (auto [j, v] : g[i]) {
                int x = xo ^ v;
                ans += cnt[x];
                for (int k = 0; k < 26; ++k) {
                    ans += cnt[x ^ (1 << k)];
                }
                ++cnt[x];
                stk.emplace_back(j, x);
            }
        }
        return ans;
    }
};
