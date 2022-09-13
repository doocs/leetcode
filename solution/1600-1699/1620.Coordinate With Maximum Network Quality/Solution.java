class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int mx = 0;
        int[] ans = new int[] {0, 0};
        for (int i = 0; i < 51; ++i) {
            for (int j = 0; j < 51; ++j) {
                int t = 0;
                for (var e : towers) {
                    double d = Math.sqrt((i - e[0]) * (i - e[0]) + (j - e[1]) * (j - e[1]));
                    if (d <= radius) {
                        t += Math.floor(e[2] / (1 + d));
                    }
                }
                if (mx < t) {
                    mx = t;
                    ans = new int[] {i, j};
                }
            }
        }
        return ans;
    }
}