class Solution {
public:
    bool isMajorityElement(vector<int>& nums, int target) {
        auto left = lower_bound(nums.begin(), nums.end(), target);
        auto right = upper_bound(nums.begin(), nums.end(), target);
        return right - left > nums.size() / 2;
    }
};