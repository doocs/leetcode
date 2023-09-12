class Solution {
    public int minimumMoves(int[][] grid) {
        List<int[]> left = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 0) {
                    left.add(new int[] {i, j});
                } else {
                    for (int k = 1; k < grid[i][j]; ++k) {
                        right.add(new int[] {i, j});
                    }
                }
            }
        }
        int n = left.size();
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            int k = Integer.bitCount(i);
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.min(f[i], f[i ^ (1 << j)] + cal(left.get(k - 1), right.get(j)));
                }
            }
        }
        return f[(1 << n) - 1];
    }

    private int cal(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}