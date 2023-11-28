class Solution {
public:
    int findMaximumLength(vector<int>& nums) {
        int n = nums.size();
        int f[n + 1];
        int pre[n + 2];
        long long s[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        memset(f, 0, sizeof(f));
        memset(pre, 0, sizeof(pre));
        for (int i = 1; i <= n; ++i) {
            pre[i] = max(pre[i], pre[i - 1]);
            f[i] = f[pre[i]] + 1;
            int j = lower_bound(s, s + n + 1, s[i] * 2 - s[pre[i]]) - s;
            pre[j] = i;
        }
        return f[n];
    }
};