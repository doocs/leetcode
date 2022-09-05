class Solution {
public:
    int countPairs(vector<int>& deliciousness) {
        unordered_map<int, int> cnt;
        for (int v : deliciousness) ++cnt[v];
        long long ans = 0;
        for (int i = 0; i < 22; ++i) {
            int s = 1 << i;
            for (auto& [a, m] : cnt) {
                int b = s - a;
                if (!cnt.count(b)) continue;
                if (a == b)
                    ans += 1ll * m * (m - 1);
                else
                    ans += 1ll * m * cnt[b];
            }
        }
        ans >>= 1;
        int mod = 1e9 + 7;
        return (int) (ans % mod);
    }
};