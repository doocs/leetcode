class Solution {
    private int n;
    private Integer[] f;

    public int selfDivisiblePermutationCount(int n) {
        this.n = n;
        f = new Integer[1 << (n + 1)];
        return dfs(0);
    }

    private int dfs(int mask) {
        if (f[mask] != null) {
            return f[mask];
        }
        int i = Integer.bitCount(mask) + 1;
        if (i > n) {
            return 1;
        }
        f[mask] = 0;
        for (int j = 1; j <= n; ++j) {
            if ((mask >> j & 1) == 0 && (i % j == 0 || j % i == 0)) {
                f[mask] += dfs(mask | 1 << j);
            }
        }
        return f[mask];
    }
}