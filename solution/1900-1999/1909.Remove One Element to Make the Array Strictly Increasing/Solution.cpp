class Solution {
public:
    bool canBeIncreasing(vector<int>& nums) {
        int n = nums.size();
        auto check = [&](int k) -> bool {
            int pre = 0;
            for (int i = 0; i < n; ++i) {
                if (i == k) {
                    continue;
                }
                if (pre >= nums[i]) {
                    return false;
                }
                pre = nums[i];
            }
            return true;
        };
        int i = 0;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        return check(i) || check(i + 1);
    }
};
