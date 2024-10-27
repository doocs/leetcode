class Solution {
    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        int[][] f = new int[k + 1][n];
        for (var g : f) {
            Arrays.fill(g, Integer.MIN_VALUE);
        }
        Arrays.fill(f[0], 0);
        for (int i = 1; i <= k; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int h = 0; h < n; ++h) {
                    f[i][j] = Math.max(
                        f[i][j], f[i - 1][h] + (j == h ? stayScore[i - 1][j] : travelScore[h][j]));
                }
            }
        }
        return Arrays.stream(f[k]).max().getAsInt();
    }
}
