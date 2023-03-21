class Solution {
public:
    int longestNiceSubarray(vector<int>& nums) {
        int ans = 0, mask = 0;
        for (int i = 0, j = 0; i < nums.size(); ++i) {
            while (mask & nums[i]) {
                mask ^= nums[j++];
            }
            ans = max(ans, i - j + 1);
            mask |= nums[i];
        }
        return ans;
    }
};