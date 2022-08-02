class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        int[] indeg = new int[n];
        for (int[] e : relations) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            ++indeg[b];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.offer(i);
                dp[i] = t;
                ans = Math.max(ans, t);
            }
        }
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : g[i]) {
                dp[j] = Math.max(dp[j], dp[i] + time[j]);
                ans = Math.max(ans, dp[j]);
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }
}