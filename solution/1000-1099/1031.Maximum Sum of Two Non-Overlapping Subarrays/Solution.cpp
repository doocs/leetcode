class Solution {
public:
    int maxSumTwoNoOverlap(vector<int>& nums, int firstLen, int secondLen) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = 0;
        for (int i = firstLen, t = 0; i + secondLen - 1 < n; ++i) {
            t = max(t, s[i] - s[i - firstLen]);
            ans = max(ans, t + s[i + secondLen] - s[i]);
        }
        for (int i = secondLen, t = 0; i + firstLen - 1 < n; ++i) {
            t = max(t, s[i] - s[i - secondLen]);
            ans = max(ans, t + s[i + firstLen] - s[i]);
        }
        return ans;
    }
};