class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];
        for (int[] arr : res) {
            Arrays.fill(arr, -1);
        }
        class Position {
            int x, y;

            public Position(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        Queue<Position> queue = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                    queue.offer(new Position(i, j));
                }
            }
        }
        int[] dirs = new int[]{-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int x = pos.x + dirs[i], y = pos.y + dirs[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && res[x][y] == -1) {
                    res[x][y] = res[pos.x][pos.y] + 1;
                    queue.offer(new Position(x, y));
                }
            }
        }
        return res;
    }
}
