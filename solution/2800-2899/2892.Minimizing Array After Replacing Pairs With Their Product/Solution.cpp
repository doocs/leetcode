class Solution {
public:
    int minArrayLength(vector<int>& nums, int k) {
        int ans = 1;
        long long y = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i];
            if (x == 0) {
                return 1;
            }
            if (x * y <= k) {
                y *= x;
            } else {
                y = x;
                ++ans;
            }
        }
        return ans;
    }
};