public class Solution {
    public double[] DicesProbability(int n) {
        int[] f = new int[7];
        for (int i = 1; i <= 6; ++i) {
            f[i] = 1;
        }
        f[0] = 0;
        
        for (int i = 2; i <= n; ++i) {
            int[] g = new int[6 * i + 1];
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j - k >= 0 && j - k < f.Length) {
                        g[j] += f[j - k];
                    }
                }
            }
            f = g;
        }
        
        double m = Math.Pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int j = n; j <= 6 * n; ++j) {
            ans[j - n] = f[j] / m;
        }
        return ans;
    }
}