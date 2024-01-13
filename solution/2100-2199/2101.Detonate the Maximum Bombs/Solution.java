class Solution {
    private int[][] bombs;

    public int maximumDetonation(int[][] bombs) {
        this.bombs = bombs;
        int n = bombs.length;
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = check(i, j);
            }
        }
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(k);
            boolean[] vis = new boolean[n];
            vis[k] = true;
            int cnt = 0;
            while (!q.isEmpty()) {
                int i = q.poll();
                ++cnt;
                for (int j = 0; j < n; ++j) {
                    if (g[i][j] && !vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    private boolean check(int i, int j) {
        if (i == j) {
            return false;
        }
        long x = bombs[i][0] - bombs[j][0];
        long y = bombs[i][1] - bombs[j][1];
        long r = bombs[i][2];
        return r * r >= x * x + y * y;
    }
}