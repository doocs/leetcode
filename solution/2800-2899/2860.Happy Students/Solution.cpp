class Solution {
public:
    int countWays(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i <= n; ++i) {
            if ((i && nums[i - 1] >= i) || (i < n && nums[i] <= i)) {
                continue;
            }
            ++ans;
        }
        return ans;
    }
};