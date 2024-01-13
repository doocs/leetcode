class Solution {
public:
    vector<int> smallerNumbersThanCurrent(vector<int>& nums) {
        vector<int> arr = nums;
        sort(arr.begin(), arr.end());
        for (int i = 0; i < nums.size(); ++i) {
            nums[i] = lower_bound(arr.begin(), arr.end(), nums[i]) - arr.begin();
        }
        return nums;
    }
};