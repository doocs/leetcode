class Solution {
public:
    int rob(vector<int>& nums) {
        int f = 0, g = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int t = g;
            g = max(g, f + nums[i]);
            f = t;
        }
        return g;
    }
};