class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] f = new int[high + 1];
        for (int i = high; i >= 0; --i) {
            long ans = i >= low && i <= high ? 1 : 0;
            if (i + zero <= high) {
                ans += f[i + zero];
            }
            if (i + one <= high) {
                ans += f[i + one];
            }
            f[i] = (int) (ans % MOD);
        }
        return f[0];
    }
}
