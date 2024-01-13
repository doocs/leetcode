class Solution {
public:
    int sumOfBeauties(vector<int>& nums) {
        int n = nums.size();
        vector<int> right(n, nums[n - 1]);
        for (int i = n - 2; i; --i) {
            right[i] = min(right[i + 1], nums[i]);
        }
        int ans = 0;
        for (int i = 1, l = nums[0]; i < n - 1; ++i) {
            int r = right[i + 1];
            if (l < nums[i] && nums[i] < r) {
                ans += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
            l = max(l, nums[i]);
        }
        return ans;
    }
};