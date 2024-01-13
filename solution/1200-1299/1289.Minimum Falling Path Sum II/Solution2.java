class Solution {
    public int minFallingPathSum(int[][] grid) {
        int f = 0, g = 0;
        int fp = -1;
        final int inf = 1 << 30;
        for (int[] row : grid) {
            int ff = inf, gg = inf;
            int ffp = -1;
            for (int j = 0; j < row.length; ++j) {
                int s = (j != fp ? f : g) + row[j];
                if (s < ff) {
                    gg = ff;
                    ff = s;
                    ffp = j;
                } else if (s < gg) {
                    gg = s;
                }
            }
            f = ff;
            g = gg;
            fp = ffp;
        }
        return f;
    }
}