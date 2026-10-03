class Solution {
public:
    long long maximumTotalDamage(vector<int>& power) {
        sort(power.begin(), power.end());
        int n = power.size();
        unordered_map<int, int> cnt;
        vector<int> nxt(n);
        for (int i = 0; i < n; ++i) {
            cnt[power[i]]++;
            nxt[i] = upper_bound(power.begin() + i + 1, power.end(), power[i] + 2) - power.begin();
        }
        vector<long long> f(n + 1);
        for (int i = n - 1; i >= 0; --i) {
            int j = i + cnt[power[i]];
            long long a = j <= n ? f[j] : 0;
            long long b = 1LL * power[i] * cnt[power[i]] + f[nxt[i]];
            f[i] = max(a, b);
        }
        return f[0];
    }
};
