class Solution {
public:
    int minRange(int a, int b, int c) {
        vector<int> nums = {a, b, c};
        sort(nums.begin(), nums.end());
        return min(nums[2] - nums[0], nums[1]);
    }
};