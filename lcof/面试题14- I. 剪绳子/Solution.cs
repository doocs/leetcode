public class Solution {
    public int CuttingRope(int n) {
        int[] f = new int[n + 1];
        f[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                f[i] = Math.Max(Math.Max(f[i], f[i - j] * j), (i - j) * j);
            }
        }
        return f[n];
    }
}
