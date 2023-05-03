class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][1 << n];
        for (int i = 0; i < n; ++i) {
            q.offer(new int[] {i, 1 << i});
            vis[i][1 << i] = true;
        }
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k > 0; --k) {
                var p = q.poll();
                int i = p[0], st = p[1];
                if (st == (1 << n) - 1) {
                    return ans;
                }
                for (int j : graph[i]) {
                    int nst = st | 1 << j;
                    if (!vis[j][nst]) {
                        vis[j][nst] = true;
                        q.offer(new int[] {j, nst});
                    }
                }
            }
        }
    }
}