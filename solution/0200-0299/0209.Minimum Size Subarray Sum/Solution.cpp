class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int n = nums.size();
        long long s = 0;
        int ans = n + 1;
        for (int i = 0, j = 0; i < n; ++i) {
            s += nums[i];
            while (j < n && s >= target) {
                ans = min(ans, i - j + 1);
                s -= nums[j++];
            }
        }
        return ans == n + 1 ? 0 : ans;
    }
};