class Solution {
public:
    int maxSum(vector<int>& nums, int k) {
        int cnt[31]{}；
        for (int x : nums) {
            for (int i = 0; i < 31; ++i) {
                if (x >> i & 1) {
                    ++cnt[i];
                }
            }
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        while (k--) {
            int x = 0;
            for (int i = 0; i < 31; ++i) {
                if (cnt[i]) {
                    x |= 1 << i;
                    --cnt[i];
                }
            }
            ans = (ans + 1LL * x * x) % mod;
        }
        return ans;
    }
};