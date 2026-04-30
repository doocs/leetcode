class Solution {
    static constexpr int MOD = 1000000007;

    long long modpow(long long a, long long e) {
        long long r = 1 % MOD;
        a %= MOD;
        while (e > 0) {
            if (e & 1) { r = (r * a) % MOD; }
            a = (a * a) % MOD;
            e >>= 1;
        }
        return r;
    }

public:
    int xorAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        int B = sqrt(n) + 1;

        vector<vector<vector<pair<int, int>>>> events(B + 1);
        for (int k = 1; k <= B; ++k) {
            events[k].resize(k);
        }

        for (auto& qq : queries) {
            int l = qq[0], r = qq[1], k = qq[2], v = qq[3];
            if (k > B) {
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (long long) nums[idx] * v % MOD;
                }
            } else {
                int res = l % k;
                int t1 = (l - res) / k;
                int t2 = (r - res) / k;
                events[k][res].push_back({t1, v});

                if (t2 + 1 <= (n - 1 - res) / k) {
                    int invv = modpow(v, MOD - 2);
                    events[k][res].push_back({t2 + 1, invv});
                }
            }
        }

        for (int k = 1; k <= B; ++k) {
            for (int res = 0; res < k; ++res) {
                auto& ev = events[k][res];
                if (ev.empty()) {
                    continue;
                }

                sort(ev.begin(), ev.end());
                vector<pair<int, int>> comp;

                for (auto& p : ev) {
                    if (!comp.empty() && comp.back().first == p.first) {
                        comp.back().second = (long long) comp.back().second * p.second % MOD;
                    } else {
                        comp.push_back(p);
                    }
                }

                long long cur = 1;
                int ptr = 0;
                int t = 0;
                for (int idx = res; idx < n; idx += k, ++t) {
                    while (ptr < comp.size() && comp[ptr].first == t) {
                        cur = (cur * comp[ptr].second) % MOD;
                        ++ptr;
                    }
                    nums[idx] = nums[idx] * cur % MOD;
                }
            }
        }

        int xr = 0;
        for (int x : nums) {
            xr ^= x;
        }

        return xr;
    }
};
