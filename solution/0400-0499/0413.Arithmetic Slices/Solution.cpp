class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int ans = 0, cnt = 0;
        int d = 3000;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i + 1] - nums[i] == d) {
                ++cnt;
            } else {
                d = nums[i + 1] - nums[i];
                cnt = 0;
            }
            ans += cnt;
        }
        return ans;
    }
};