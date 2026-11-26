class Solution {
public:
    long long minCost(vector<int>& nums) {
        int n = nums.size();
        long long ans = LLONG_MAX;
        int l = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            if (r == n - 1 || x != nums[r + 1]) {
                if (r > 0 && l < n - 1) {
                    ans = min(ans, 1LL * (l + n - r - 1) * x);
                }
                l = r + 1;
            }
        }
        return ans;
    }
};