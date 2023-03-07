class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1;
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            int t = nums[i] + nums[j];
            if (t < k) {
                ans = max(ans, t);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
};