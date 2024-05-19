class Solution {
public:
    long long sumDigitDifferences(vector<int>& nums) {
        int n = nums.size();
        int m = floor(log10(nums[0])) + 1;
        int cnt[10];
        long long ans = 0;
        for (int k = 0; k < m; ++k) {
            memset(cnt, 0, sizeof(cnt));
            for (int i = 0; i < n; ++i) {
                ++cnt[nums[i] % 10];
                nums[i] /= 10;
            }
            for (int i = 0; i < 10; ++i) {
                ans += 1LL * (cnt[i] * (n - cnt[i]));
            }
        }
        return ans / 2;
    }
};