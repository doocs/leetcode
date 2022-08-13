class Solution {
public:
    int smallestRangeI(vector<int>& nums, int k) {
        int mx = *max_element(nums.begin(), nums.end());
        int mi = *min_element(nums.begin(), nums.end());
        return max(0, mx - mi - k * 2);
    }
};