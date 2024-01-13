class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int numMagicSquaresInside(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += check(i, j);
            }
        }
        return ans;
    }

    private int check(int i, int j) {
        if (i + 3 > m || j + 3 > n) {
            return 0;
        }
        int[] cnt = new int[16];
        int[] row = new int[3];
        int[] col = new int[3];
        int a = 0, b = 0;
        for (int x = i; x < i + 3; ++x) {
            for (int y = j; y < j + 3; ++y) {
                int v = grid[x][y];
                if (v < 1 || v > 9 || ++cnt[v] > 1) {
                    return 0;
                }
                row[x - i] += v;
                col[y - j] += v;
                if (x - i == y - j) {
                    a += v;
                }
                if (x - i + y - j == 2) {
                    b += v;
                }
            }
        }
        if (a != b) {
            return 0;
        }
        for (int k = 0; k < 3; ++k) {
            if (row[k] != a || col[k] != a) {
                return 0;
            }
        }
        return 1;
    }
}