class Solution {
    private List<Integer> strength;
    private Integer[] f;
    private int k;
    private int n;

    public int findMinimumTime(List<Integer> strength, int K) {
        n = strength.size();
        f = new Integer[1 << n];
        k = K;
        this.strength = strength;
        return dfs(0);
    }

    private int dfs(int i) {
        if (i == (1 << n) - 1) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int cnt = Integer.bitCount(i);
        int x = 1 + cnt * k;
        f[i] = 1 << 30;
        for (int j = 0; j < n; ++j) {
            if ((i >> j & 1) == 0) {
                f[i] = Math.min(f[i], dfs(i | 1 << j) + (strength.get(j) + x - 1) / x);
            }
        }
        return f[i];
    }
}
