using ll = long long;

class Solution {
public:
    vector<ll> f;
    vector<int> power;
    int n;

    long long minimumTime(vector<int>& power) {
        n = power.size();
        f.assign(1 << n, -1);
        this->power = power;
        return dfs(0);
    }

    ll dfs(int mask) {
        if (f[mask] != -1) return f[mask];
        int cnt = __builtin_popcount(mask);
        if (cnt == n) return 0;
        ll ans = LONG_MAX;
        for (int i = 0; i < n; ++i) {
            if ((mask >> i) & 1) continue;
            ans = min(ans, dfs(mask | 1 << i) + (power[i] + cnt) / (cnt + 1));
        }
        f[mask] = ans;
        return ans;
    }
};