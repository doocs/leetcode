class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] s1 = new int[m + 1][n + 2];
        int[][] s2 = new int[m + 1][n + 2];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s1[i][j] = s1[i - 1][j - 1] + grid[i - 1][j - 1];
                s2[i][j] = s2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }
        TreeSet<Integer> ss = new TreeSet<>();
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int l = Math.min(Math.min(i - 1, m - i), Math.min(j - 1, n - j));
                ss.add(grid[i - 1][j - 1]);
                for (int k = 1; k <= l; ++k) {
                    int a = s1[i + k][j] - s1[i][j - k];
                    int b = s1[i][j + k] - s1[i - k][j];
                    int c = s2[i][j - k] - s2[i - k][j];
                    int d = s2[i + k][j] - s2[i][j + k];
                    ss.add(a + b + c + d - grid[i + k - 1][j - 1] + grid[i - k - 1][j - 1]);
                }
                while (ss.size() > 3) {
                    ss.pollFirst();
                }
            }
        }
        int[] ans = new int[ss.size()];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = ss.pollLast();
        }
        return ans;
    }
}