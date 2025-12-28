class Solution {
public:
    long long maximumScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> suf(n);
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = min((long long) nums[i], suf[i + 1]);
        }
        long long ans = LLONG_MIN;
        long long pre = 0;
        for (int i = 0; i < n - 1; ++i) {
            pre += nums[i];
            ans = max(ans, pre - suf[i + 1]);
        }
        return ans;
    }
};
