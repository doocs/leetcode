class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int f = nums[0], g = nums[0], ans = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int ff = f, gg = g;
            f = max({nums[i], ff * nums[i], gg * nums[i]});
            g = min({nums[i], ff * nums[i], gg * nums[i]});
            ans = max(ans, f);
        }
        return ans;
    }
};