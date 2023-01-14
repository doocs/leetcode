class Solution {
public:
    int minMaxGame(vector<int>& nums) {
        for (int n = nums.size(); n > 1;) {
            n >>= 1;
            for (int i = 0; i < n; ++i) {
                int a = nums[i << 1], b = nums[i << 1 | 1];
                nums[i] = i % 2 == 0 ? min(a, b) : max(a, b);
            }
        }
        return nums[0];
    }
};