class Solution {
    private boolean[][][] vis;
    private int[][] room;
    private int ans;

    public int numberOfCleanRooms(int[][] room) {
        vis = new boolean[room.length][room[0].length][4];
        this.room = room;
        dfs(0, 0, 0);
        return ans;
    }

    private void dfs(int i, int j, int k) {
        if (vis[i][j][k]) {
            return;
        }
        int[] dirs = {0, 1, 0, -1, 0};
        ans += room[i][j] == 0 ? 1 : 0;
        room[i][j] = -1;
        vis[i][j][k] = true;
        int x = i + dirs[k], y = j + dirs[k + 1];
        if (x >= 0 && x < room.length && y >= 0 && y < room[0].length && room[x][y] != 1) {
            dfs(x, y, k);
        } else {
            dfs(i, j, (k + 1) % 4);
        }
    }
}