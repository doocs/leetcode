class Solution {
public:
    int longestNiceSubarray(vector<int>& nums) {
        int t = 0, j = 0;
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            while (t & nums[i]) {
                t ^= nums[j++];
            }
            t |= nums[i];
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};