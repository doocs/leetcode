class Solution {
public:
    int minimumSplits(vector<int>& nums) {
        int ans = 1, x = nums[0];
        for (int v : nums) {
            x = gcd(x, v);
            if (x == 1) {
                x = v;
                ++ans;
            }
        }
        return ans;
    }
};