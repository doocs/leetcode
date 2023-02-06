using ll = long long;

class Solution {
public:
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end());
        int m = rides.size();
        vector<ll> f(m);
        vector<int> x(3);
        function<ll(int)> dfs = [&](int i) -> ll {
            if (i >= m) return 0;
            if (f[i]) return f[i];
            int s = rides[i][0], e = rides[i][1], t = rides[i][2];
            x[0] = e;
            int j = lower_bound(rides.begin() + i + 1, rides.end(), x, [&](auto& l, auto& r) -> bool { return l[0] < r[0]; }) - rides.begin();
            ll ans = max(dfs(i + 1), dfs(j) + e - s + t);
            f[i] = ans;
            return ans;
        };
        return dfs(0);
    }
};