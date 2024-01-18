class Solution {
public:
    long long maximumSumScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        long long ans = INT_MIN;
        for (int i = 0; i < n; ++i) ans = max(ans, max(s[i + 1], s[n] - s[i]));
        return ans;
    }
};