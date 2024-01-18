class Solution {
public:
    int minimumIncompatibility(vector<int>& nums, int k) {
        int n = nums.size();
        int m = n / k;
        int g[1 << n];
        memset(g, -1, sizeof(g));
        for (int i = 1; i < 1 << n; ++i) {
            if (__builtin_popcount(i) != m) {
                continue;
            }
            unordered_set<int> s;
            int mi = 20, mx = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    if (s.count(nums[j])) {
                        break;
                    }
                    s.insert(nums[j]);
                    mi = min(mi, nums[j]);
                    mx = max(mx, nums[j]);
                }
            }
            if (s.size() == m) {
                g[i] = mx - mi;
            }
        }
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 0; i < 1 << n; ++i) {
            if (f[i] == 0x3f3f3f3f) {
                continue;
            }
            unordered_set<int> s;
            int mask = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 0 && !s.count(nums[j])) {
                    s.insert(nums[j]);
                    mask |= 1 << j;
                }
            }
            if (s.size() < m) {
                continue;
            }
            for (int j = mask; j; j = (j - 1) & mask) {
                if (g[j] != -1) {
                    f[i | j] = min(f[i | j], f[i] + g[j]);
                }
            }
        }
        return f[(1 << n) - 1] == 0x3f3f3f3f ? -1 : f[(1 << n) - 1];
    }
};