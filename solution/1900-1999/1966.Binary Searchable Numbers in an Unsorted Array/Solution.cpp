class Solution {
public:
    int binarySearchableNumbers(vector<int>& nums) {
        int n = nums.size();
        vector<int> ok(n, 1);
        int mx = -1000000, mi = 1000000;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < mx) {
                ok[i] = 0;
            }
            mx = max(mx, nums[i]);
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] > mi) {
                ok[i] = 0;
            }
            mi = min(mi, nums[i]);
            ans += ok[i];
        }
        return ans;
    }
};