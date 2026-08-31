class Solution {
public:
    long long minMergeCost(vector<vector<int>>& lists) {
        int n = lists.size();
        vector<int> vals;
        for (auto& v : lists) {
            vals.insert(vals.end(), v.begin(), v.end());
        }
        sort(vals.begin(), vals.end());
        vals.erase(unique(vals.begin(), vals.end()), vals.end());

        vector<int> cnt(1 << n);
        vector<int> med(1 << n);
        for (int i = 1; i < 1 << n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    cnt[i] += lists[j].size();
                }
            }
            int need = (cnt[i] + 1) / 2;
            int l = 0, r = vals.size() - 1;
            while (l < r) {
                int mid = (l + r) >> 1;
                int le = 0;
                for (int b = i; b; b &= b - 1) {
                    int id = __builtin_ctz(b);
                    le += upper_bound(lists[id].begin(), lists[id].end(), vals[mid]) - lists[id].begin();
                    if (le >= need) {
                        break;
                    }
                }
                if (le >= need) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            med[i] = vals[l];
        }

        vector<long long> f(1 << n, 1e18);
        for (int i = 1; i < 1 << n; ++i) {
            if (__builtin_popcount(i) == 1) {
                f[i] = 0;
                continue;
            }
            for (int j = (i - 1) & i; j; j = (j - 1) & i) {
                int k = i ^ j;
                if (j <= k) {
                    f[i] = min(f[i], f[j] + f[k] + abs(med[j] - med[k]));
                }
            }
            f[i] += cnt[i];
        }
        return f[(1 << n) - 1];
    }
};
