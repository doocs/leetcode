class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        int ans = 1;
        for (int i = 1, cnt = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                ans = max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }
};