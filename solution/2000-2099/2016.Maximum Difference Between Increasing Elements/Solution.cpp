class Solution {
public:
    int maximumDifference(vector<int>& nums) {
        int mi = nums[0];
        int ans = -1;
        for (int i = 1, n = nums.size(); i < n; ++i) {
            if (nums[i] > mi)
                ans = max(ans, nums[i] - mi);
            else
                mi = nums[i];
        }
        return ans;
    }
};