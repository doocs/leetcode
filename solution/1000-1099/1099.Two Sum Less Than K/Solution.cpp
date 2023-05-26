class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1;
        for (int i = 0, j = nums.size() - 1; i < j;) {
            int s = nums[i] + nums[j];
            if (s < k) {
                ans = max(ans, s);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
};