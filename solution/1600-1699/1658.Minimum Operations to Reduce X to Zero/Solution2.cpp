class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        x = accumulate(nums.begin(), nums.end(), 0) - x;
        int n = nums.size();
        int ans = 1 << 30;
        for (int i = 0, j = 0, s = 0; i < n; ++i) {
            s += nums[i];
            while (j <= i && s > x) {
                s -= nums[j++];
            }
            if (s == x) {
                ans = min(ans, n - (i - j + 1));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
};