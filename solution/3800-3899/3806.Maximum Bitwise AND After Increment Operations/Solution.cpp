class Solution {
public:
    int maximumAND(const vector<int>& nums, int k, int m) {
        int max_val = ranges::max(nums) + k;
        int mx = max_val > 0 ? 32 - __builtin_clz(max_val) : 0;

        int ans = 0;
        vector<int> cost(nums.size());

        for (int bit = mx - 1; bit >= 0; bit--) {
            int target = ans | (1 << bit);
            for (size_t i = 0; i < nums.size(); i++) {
                int x = nums[i];
                int diff = target & ~x;
                int j = diff == 0 ? 0 : 32 - __builtin_clz(diff);
                long long mask = (1L << j) - 1;
                cost[i] = (target & mask) - (x & mask);
            }

            ranges::sort(cost);
            long long sum = accumulate(cost.begin(), cost.begin() + m, 0LL);
            if (sum <= k) {
                ans = target;
            }
        }

        return ans;
    }
};
