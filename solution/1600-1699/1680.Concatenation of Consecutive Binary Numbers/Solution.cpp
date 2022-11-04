class Solution {
public:
    const int mod = 1e9 + 7;

    int concatenatedBinary(int n) {
        long ans = 0;
        int shift = 0;
        for (int i = 1; i <= n; ++i) {
            if ((i & (i - 1)) == 0) {
                ++shift;
            }
            ans = ((ans << shift) + i) % mod;
        }
        return ans;
    }
};