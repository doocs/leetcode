class Solution {
public:
    int maxSumAfterOperation(vector<int>& nums) {
        int f = 0, g = 0;
        int ans = INT_MIN;
        for (int x : nums) {
            int ff = max(f, 0) + x;
            int gg = max(max(f, 0) + x * x, g + x);
            f = ff;
            g = gg;
            ans = max({ans, f, g});
        }
        return ans;
    }
};