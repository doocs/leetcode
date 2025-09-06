class Solution {
    public int maxWeight(int[] weights, int w1, int w2) {
        int[][] f = new int[w1 + 1][w2 + 1];
        for (int x : weights) {
            for (int j = w1; j >= 0; --j) {
                for (int k = w2; k >= 0; --k) {
                    if (x <= j) {
                        f[j][k] = Math.max(f[j][k], f[j - x][k] + x);
                    }
                    if (x <= k) {
                        f[j][k] = Math.max(f[j][k], f[j][k - x] + x);
                    }
                }
            }
        }
        return f[w1][w2];
    }
}
