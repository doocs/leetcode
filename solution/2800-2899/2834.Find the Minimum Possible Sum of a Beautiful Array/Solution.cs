public class Solution {
    public int MinimumPossibleSum(int n, int target) {
        const int mod = (int) 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (int) ((1L + n) * n / 2 % mod);
        }
        long a = (1L + m) * m / 2 % mod;
        long b = ((1L * target + target + n - m - 1) * (n - m) / 2) % mod;
        return (int) ((a + b) % mod);
    }
}