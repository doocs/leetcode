class Solution {
public:
    int minimumSum(vector<int>& nums) {
        int n = nums.size();
        const int inf = 1 << 30;
        int right[n + 1];
        right[n] = inf;
        for (int i = n - 1; ~i; --i) {
            right[i] = min(right[i + 1], nums[i]);
        }
        int ans = inf, left = inf;
        for (int i = 0; i < n; ++i) {
            if (left < nums[i] && right[i + 1] < nums[i]) {
                ans = min(ans, left + nums[i] + right[i + 1]);
            }
            left = min(left, nums[i]);
        }
        return ans == inf ? -1 : ans;
    }
};