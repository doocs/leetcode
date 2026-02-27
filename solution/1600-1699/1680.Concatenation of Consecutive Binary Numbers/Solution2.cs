public class Solution {
    public int ConcatenatedBinary(int n) {
        const int mod = 1000000007;
        long ans = 0;
        int shift = 0;
        for (int i = 1; i <= n; ++i) {
            if ((i & (i - 1)) == 0) {
                ++shift;
            }
            ans = ((ans << shift) | i) % mod;
        }
        return (int)ans;
    }
}
