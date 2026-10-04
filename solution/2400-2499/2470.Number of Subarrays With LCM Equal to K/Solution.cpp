class Solution {
public:
    int subarrayLCM(vector<int>& nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int a = 1;
            for (int j = i; j < nums.size(); ++j) {
                if (k % nums[j] != 0) {
                    break;
                }
                a = lcm(a, nums[j]);
                ans += a == k;
            }
        }
        return ans;
    }
};
