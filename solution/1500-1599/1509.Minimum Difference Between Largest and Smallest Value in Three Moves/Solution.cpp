class Solution {
public:
    int minDifference(vector<int>& nums) {
        int n = nums.size();
        if (n < 5) {
            return 0;
        }
        sort(nums.begin(), nums.end());
        long long ans = 1L << 60;
        for (int l = 0; l <= 3; ++l) {
            int r = 3 - l;
            ans = min(ans, 1LL * nums[n - 1 - r] - nums[l]);
        }
        return ans;
    }
};