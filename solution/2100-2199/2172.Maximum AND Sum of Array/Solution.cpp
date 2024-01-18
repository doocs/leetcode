class Solution {
public:
    int maximumANDSum(vector<int>& nums, int numSlots) {
        int n = nums.size();
        int m = numSlots << 1;
        int f[1 << m];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < 1 << m; ++i) {
            int cnt = __builtin_popcount(i);
            if (cnt > n) {
                continue;
            }
            for (int j = 0; j < m; ++j) {
                if (i >> j & 1) {
                    f[i] = max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j / 2 + 1)));
                }
            }
        }
        return *max_element(f, f + (1 << m));
    }
};