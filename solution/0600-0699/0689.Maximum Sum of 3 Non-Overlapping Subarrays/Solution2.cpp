class Solution {
public:
    vector<int> maxSumOfThreeSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> s(n + 1, 0);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }

        vector<vector<int>> pre(n, vector<int>(2, 0));
        vector<vector<int>> suf(n, vector<int>(2, 0));

        for (int i = 0, t = 0, idx = 0; i < n - k + 1; ++i) {
            int cur = s[i + k] - s[i];
            if (cur > t) {
                pre[i + k - 1] = {cur, i};
                t = cur;
                idx = i;
            } else {
                pre[i + k - 1] = {t, idx};
            }
        }

        for (int i = n - k, t = 0, idx = 0; i >= 0; --i) {
            int cur = s[i + k] - s[i];
            if (cur >= t) {
                suf[i] = {cur, i};
                t = cur;
                idx = i;
            } else {
                suf[i] = {t, idx};
            }
        }

        vector<int> ans;
        for (int i = k, t = 0; i < n - 2 * k + 1; ++i) {
            int cur = s[i + k] - s[i] + pre[i - 1][0] + suf[i + k][0];
            if (cur > t) {
                ans = {pre[i - 1][1], i, suf[i + k][1]};
                t = cur;
            }
        }

        return ans;
    }
};