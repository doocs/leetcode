class Solution {
public:
    int maxHappyGroups(int batchSize, vector<int>& groups) {
        using ll = long long;
        unordered_map<ll, int> f;
        ll state = 0;
        int ans = 0;
        for (auto& v : groups) {
            int i = v % batchSize;
            ans += i == 0;
            if (i) {
                state += 1ll << (i * 5);
            }
        }
        function<int(ll, int)> dfs = [&](ll state, int mod) {
            if (f.count(state)) {
                return f[state];
            }
            int res = 0;
            int x = mod == 0;
            for (int i = 1; i < batchSize; ++i) {
                if (state >> (i * 5) & 31) {
                    int t = dfs(state - (1ll << (i * 5)), (mod + i) % batchSize);
                    res = max(res, t + x);
                }
            }
            return f[state] = res;
        };
        ans += dfs(state, 0);
        return ans;
    }
};