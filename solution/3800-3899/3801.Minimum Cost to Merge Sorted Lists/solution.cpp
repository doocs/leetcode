class Solution {
public:
    using ll = long long;
    using t3 = tuple<ll, ll, ll>;
    ll enc(ll a, ll b, ll c)
    {
        return (a << 26) + (b << 15) + (c);
    }
    t3 dec(ll l)
    {
        ll c = l & ((1 << 15) - 1);
        ll a = l >> 26;
        ll b = (l >> 15) & ((1 << 11) - 1);
        return {a, b, c};
    }
    long long minMergeCost(vector<vector<int>>& lists) {
        int n = lists.size();
        int m = 1 << n;

        vector<ll> vals;
        for(auto& v: lists)
        {
            for(auto x: v) vals.push_back(x);
        }
        sort(vals.begin(), vals.end());
        vals.erase(unique(vals.begin(), vals.end()), vals.end());

        vector<ll> cnt(m), med(m);
        for(int i = 1; i < m; ++i)
        {
            for(int j = 0; j < n; ++j)
            {
                if((i >> j) & 1) cnt[i] += lists[j].size();
            }
        }
        for(int mask = 1; mask < m; ++mask)
        {
            ll need = (cnt[mask] + 1) / 2;
            int l = 0, r = vals.size() - 1;

            while(l < r)
            {
                int mid = (l + r) / 2;
                ll le = 0;

                for(int b = mask; b; b &= b - 1)
                {
                    int id = __builtin_ctz(b);
                    le += upper_bound(lists[id].begin(), lists[id].end(), vals[mid]) - lists[id].begin();
                    if(le >= need) break;
                }

                if(le >= need) r = mid;
                else l = mid + 1;
            }

            med[mask] = vals[l];
        }
        vector<ll> dp(m, LLONG_MAX);
        for(int i = 1; i < m; ++i)
        {
            if(__builtin_popcount(i) == 1) 
            {
                dp[i] = 0;
                continue;
            }
            for(int j = (i - 1) & i; j > 0; j = (j - 1) & i)
            {
                int k = (i ^ j) & i;
                dp[i] = min(dp[i], dp[j] + dp[k] + abs(med[j] - med[k]));
            }
            dp[i] += cnt[i];
            //cout << i << " " << cnt[i] << " " << dp[i] << endl;
        }
        return dp[m - 1];
    }
};
