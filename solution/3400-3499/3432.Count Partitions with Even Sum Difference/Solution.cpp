class Solution {
public:
    int countPartitions(vector<int>& nums) {
        int l = 0, r = accumulate(nums.begin(), nums.end(), 0);
        int ans = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            l += nums[i];
            r -= nums[i];
            if ((l - r) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
