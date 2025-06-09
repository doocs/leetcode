class Solution {
public:
    bool canMakeEqual(vector<int>& nums, int k) {
        auto check = [&](int target, int k) -> bool {
            int n = nums.size();
            int cnt = 0, sign = 1;
            for (int i = 0; i < n - 1; ++i) {
                int x = nums[i] * sign;
                if (x == target) {
                    sign = 1;
                } else {
                    sign = -1;
                    ++cnt;
                }
            }
            return cnt <= k && nums[n - 1] * sign == target;
        };
        return check(nums[0], k) || check(-nums[0], k);
    }
};