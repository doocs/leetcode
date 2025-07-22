class Solution {
public:
    int getSum(vector<int>& nums) {
        const int mod = 1e9 + 7;
        long long s = nums[0], t = nums[0], ans = nums[0];
        int f = 1, g = 1;
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i - 1], y = nums[i];
            if (y - x == 1) {
                ++f;
                s += 1LL * f * y;
                ans = (ans + s) % mod;
            } else {
                f = 1;
                s = y;
            }
            if (y - x == -1) {
                ++g;
                t += 1LL * g * y;
                ans = (ans + t) % mod;
            } else {
                g = 1;
                t = y;
            }
            if (abs(y - x) != 1) {
                ans = (ans + y) % mod;
            }
        }
        return ans;
    }
};
