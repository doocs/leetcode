class Solution {
    public int minKnightMoves(int x, int y) {
        x += 310;
        y += 310;
        int ans = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {310, 310});
        boolean[][] vis = new boolean[700][700];
        vis[310][310] = true;
        int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                if (p[0] == x && p[1] == y) {
                    return ans;
                }
                for (int[] dir : dirs) {
                    int c = p[0] + dir[0];
                    int d = p[1] + dir[1];
                    if (!vis[c][d]) {
                        vis[c][d] = true;
                        q.offer(new int[] {c, d});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}