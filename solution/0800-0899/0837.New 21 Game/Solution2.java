class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1.0;
        }
        double[] f = new double[k + maxPts];
        for (int i = k; i < Math.min(n + 1, k + maxPts); ++i) {
            f[i] = 1;
        }
        f[k - 1] = Math.min(n - k + 1, maxPts) * 1.0 / maxPts;
        for (int i = k - 2; i >= 0; --i) {
            f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
        }
        return f[0];
    }
}