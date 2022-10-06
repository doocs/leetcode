class Solution {
public:
    vector<int> largestSubarray(vector<int>& nums, int k) {
        auto pos = max_element(nums.begin(), nums.begin() + nums.size() - k + 1);
        return {pos, pos + k};
    }
};