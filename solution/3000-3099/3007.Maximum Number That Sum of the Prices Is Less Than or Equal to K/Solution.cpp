class Solution {
public:
    long long findMaximumNumber(long long k, int x) {
        using ll = long long;
        ll l = 1, r = 1e17;
        ll num = 0;
        ll f[65][65];
        auto dfs = [&](auto&& dfs, int pos, int cnt, bool limit) -> ll {
            if (pos == 0) {
                return cnt;
            }
            if (!limit && f[pos][cnt] != -1) {
                return f[pos][cnt];
            }
            int up = limit ? num >> (pos - 1) & 1 : 1;
            ll ans = 0;
            for (int i = 0; i <= up; ++i) {
                ans += dfs(dfs, pos - 1, cnt + (i == 1 && pos % x == 0), limit && i == up);
            }
            if (!limit) {
                f[pos][cnt] = ans;
            }
            return ans;
        };
        while (l < r) {
            ll mid = (l + r + 1) >> 1;
            num = mid;
            memset(f, -1, sizeof(f));
            int pos = 64 - __builtin_clzll(mid);
            if (dfs(dfs, pos, 0, true) <= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
