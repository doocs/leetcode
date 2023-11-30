class Solution {
public:
    int sumOfFlooredPairs(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int mx = *max_element(nums.begin(), nums.end());
        vector<int> cnt(mx + 1);
        vector<int> s(mx + 1);
        for (int x : nums) {
            ++cnt[x];
        }
        for (int i = 1; i <= mx; ++i) {
            s[i] = s[i - 1] + cnt[i];
        }
        long long ans = 0;
        for (int y = 1; y <= mx; ++y) {
            if (cnt[y]) {
                for (int d = 1; d * y <= mx; ++d) {
                    ans += 1LL * cnt[y] * d * (s[min(mx, d * y + y - 1)] - s[d * y - 1]);
                    ans %= mod;
                }
            }
        }
        return ans;
    }
};