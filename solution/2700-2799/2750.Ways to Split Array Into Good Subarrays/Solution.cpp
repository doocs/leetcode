class Solution {
public:
    int numberOfGoodSubarraySplits(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int ans = 1, j = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (j > -1) {
                ans = 1LL * ans * (i - j) % mod;
            }
            j = i;
        }
        return j == -1 ? 0 : ans;
    }
};