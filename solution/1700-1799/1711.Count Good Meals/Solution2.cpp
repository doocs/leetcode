class Solution {
public:
    const int mod = 1e9 + 7;

    int countPairs(vector<int>& deliciousness) {
        unordered_map<int, int> cnt;
        for (int& d : deliciousness) ++cnt[d];
        long long ans = 0;
        for (int i = 0; i < 22; ++i) {
            int s = 1 << i;
            for (auto& [a, m] : cnt) {
                int b = s - a;
                if (!cnt.count(b)) continue;
                ans += 1ll * m * (a == b ? (m - 1) : cnt[b]);
            }
        }
        ans >>= 1;
        return ans % mod;
    }
};