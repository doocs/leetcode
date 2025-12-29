class Solution {
public:
    int sumOfBlocks(int n) {
        const int mod = 1e9 + 7;
        long long ans = 0;
        int k = 1;
        for (int i = 1; i <= n; ++i) {
            long long x = 1;
            for (int j = k; j < k + i; ++j) {
                x = x * j % mod;
            }
            ans = (ans + x) % mod;
            k += i;
        }
        return ans;
    }
};
