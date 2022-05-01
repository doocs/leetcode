class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        char[][] g = new char[m][n];
        for (int[] e : guards) {
            int r = e[0], c = e[1];
            g[r][c] = 'g';
        }
        for (int[] e : walls) {
            int r = e[0], c = e[1];
            g[r][c] = 'w';
        }
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int[] p : guards) {
            for (int[] dir : dirs) {
                int a = dir[0], b = dir[1];
                int x = p[0], y = p[1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && g[x + a][y + b] != 'w' && g[x + a][y + b] != 'g') {
                    x += a;
                    y += b;
                    g[x][y] = 'v';
                }
            }
        }
        int ans = 0;
        for (char[] row : g) {
            for (char v : row) {
                if (v == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}