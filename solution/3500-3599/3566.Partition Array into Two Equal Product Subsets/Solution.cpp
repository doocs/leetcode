class Solution {
public:
    bool checkEqualPartitions(vector<int>& nums, long long target) {
        int n = nums.size();
        for (int i = 0; i < 1 << n; ++i) {
            long long x = 1, y = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    x *= nums[j];
                } else {
                    y *= nums[j];
                }
                if (x > target || y > target) {
                    break;
                }
            }
            if (x == target && y == target) {
                return true;
            }
        }
        return false;
    }
};