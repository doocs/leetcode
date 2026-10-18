class Solution {
    public int[] lastVisitedCell(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int r = 0, c = 0;
        boolean[][] vis = new boolean[m][n];
        while (true) {
            int[] cur = {r, c};
            vis[r][c] = true;
            switch (grid[r][c]) {
            case 'L' -> c--;
            case 'R' -> c++;
            case 'U' -> r--;
            case 'D' -> r++;
            }
            if (r >= 0 && r < m && c >= 0 && c < n && !vis[r][c]) {
                continue;
            }
            return cur;
        }
    }
}