public class Solution {
    public int FirstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.Length;
        long[] f = new long[n];
        int mod = (int)1e9 + 7;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
        }
        return (int)f[n - 1];
    }
}