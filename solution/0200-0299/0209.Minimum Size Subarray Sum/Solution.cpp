class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            int j = lower_bound(s.begin(), s.end(), s[i] + target) - s.begin();
            if (j <= n) {
                ans = min(ans, j - i);
            }
        }
        return ans <= n ? ans : 0;
    }
};