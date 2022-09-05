class Solution {
    private int n;

    public int snakesAndLadders(int[][] board) {
        n = board.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        boolean[] vis = new boolean[n * n + 1];
        vis[1] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int curr = q.poll();
                if (curr == n * n) {
                    return ans;
                }
                for (int k = curr + 1; k <= Math.min(curr + 6, n * n); ++k) {
                    int[] p = get(k);
                    int next = k;
                    int i = p[0], j = p[1];
                    if (board[i][j] != -1) {
                        next = board[i][j];
                    }
                    if (!vis[next]) {
                        vis[next] = true;
                        q.offer(next);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    private int[] get(int x) {
        int i = (x - 1) / n, j = (x - 1) % n;
        if (i % 2 == 1) {
            j = n - 1 - j;
        }
        return new int[] {n - 1 - i, j};
    }
}