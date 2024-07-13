class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int m = days[days.length - 1];
        int[] f = new int[m + 1];
        final int[] valid = {1, 7, 30};
        for (int i = 1, j = 0; i <= m; ++i) {
            if (i == days[j]) {
                f[i] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; ++k) {
                    int c = costs[k], v = valid[k];
                    f[i] = Math.min(f[i], f[Math.max(0, i - v)] + c);
                }
                ++j;
            } else {
                f[i] = f[i - 1];
            }
        }
        return f[m];
    }
}