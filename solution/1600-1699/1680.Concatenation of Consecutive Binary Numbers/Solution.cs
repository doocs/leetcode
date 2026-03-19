public class Solution {
    public int ConcatenatedBinary(int n) {
        const int mod = 1000000007;
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            int bitLength = 32 - System.Numerics.BitOperations.LeadingZeroCount((uint)i);
            ans = ((ans << bitLength) | i) % mod;
        }
        return (int)ans;
    }
}
