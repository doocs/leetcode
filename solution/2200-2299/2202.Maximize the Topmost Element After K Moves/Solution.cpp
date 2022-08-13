class Solution {
public:
    int maximumTop(vector<int>& nums, int k) {
        if (k == 0) return nums[0];
        int n = nums.size();
        if (n == 1) {
            if (k % 2) return -1;
            return nums[0];
        }
        int ans = -1;
        for (int i = 0; i < min(k - 1, n); ++i) ans = max(ans, nums[i]);
        if (k < n) ans = max(ans, nums[k]);
        return ans;
    }
};