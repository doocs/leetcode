class Solution {
public:
    bool canBeIncreasing(vector<int>& nums) {
        int i = 1, n = nums.size();
        for (; i < n && nums[i - 1] < nums[i]; ++i)
            ;
        return check(nums, i - 1) || check(nums, i);
    }

    bool check(vector<int>& nums, int i) {
        int prev = 0;
        for (int j = 0; j < nums.size(); ++j) {
            if (i == j) continue;
            if (prev >= nums[j]) return false;
            prev = nums[j];
        }
        return true;
    }
};