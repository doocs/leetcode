class Solution {
public:
    int maxSubarraySumCircular(vector<int>& nums) {
        int s1 = nums[0], s2 = nums[0], f1 = nums[0], f2 = nums[0], total = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            total += nums[i];
            f1 = nums[i] + max(f1, 0);
            f2 = nums[i] + min(f2, 0);
            s1 = max(s1, f1);
            s2 = min(s2, f2);
        }
        return s1 > 0 ? max(s1, total - s2) : s1;
    }
};