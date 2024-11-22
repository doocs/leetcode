class Solution {
    int n, m = 9, ans;
    int[][][] dist;
    int[][] end;
    String[] pieces;
    int[][] positions;
    int[][] rookDirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] bishopDirs = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int[][] queenDirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public int countCombinations(String[] pieces, int[][] positions) {
        n = pieces.length;
        dist = new int[n][m][m];
        end = new int[n][3];
        ans = 0;
        this.pieces = pieces;
        this.positions = positions;

        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= n) {
            ans++;
            return;
        }

        int x = positions[i][0], y = positions[i][1];
        resetDist(i);
        dist[i][x][y] = 0;
        end[i] = new int[] {x, y, 0};

        if (checkStop(i, x, y, 0)) {
            dfs(i + 1);
        }

        int[][] dirs = getDirs(pieces[i]);
        for (int[] dir : dirs) {
            resetDist(i);
            dist[i][x][y] = 0;
            int nx = x + dir[0], ny = y + dir[1], nt = 1;

            while (isValid(nx, ny) && checkPass(i, nx, ny, nt)) {
                dist[i][nx][ny] = nt;
                end[i] = new int[] {nx, ny, nt};
                if (checkStop(i, nx, ny, nt)) {
                    dfs(i + 1);
                }
                nx += dir[0];
                ny += dir[1];
                nt++;
            }
        }
    }

    private void resetDist(int i) {
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < m; k++) {
                dist[i][j][k] = -1;
            }
        }
    }

    private boolean checkStop(int i, int x, int y, int t) {
        for (int j = 0; j < i; j++) {
            if (dist[j][x][y] >= t) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPass(int i, int x, int y, int t) {
        for (int j = 0; j < i; j++) {
            if (dist[j][x][y] == t) {
                return false;
            }
            if (end[j][0] == x && end[j][1] == y && end[j][2] <= t) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int x, int y) {
        return x >= 1 && x < m && y >= 1 && y < m;
    }

    private int[][] getDirs(String piece) {
        char c = piece.charAt(0);
        return switch (c) {
            case 'r' -> rookDirs;
            case 'b' -> bishopDirs;
            default -> queenDirs;
        };
    }
}
