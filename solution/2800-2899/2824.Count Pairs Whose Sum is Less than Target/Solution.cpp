class Solution {
public:
    int countPairs(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int j = 0; j < nums.size(); ++j) {
            int i = lower_bound(nums.begin(), nums.begin() + j, target - nums[j]) - nums.begin();
            ans += i;
        }
        return ans;
    }
};