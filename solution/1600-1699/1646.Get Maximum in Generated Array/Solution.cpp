class Solution {
public:
    int getMaximumGenerated(int n) {
        if (n < 2) {
            return n;
        }
        int nums[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= n; ++i) {
            nums[i] = i % 2 == 0 ? nums[i >> 1] : nums[i >> 1] + nums[(i >> 1) + 1];
        }
        return *max_element(nums, nums + n + 1);
    }
};