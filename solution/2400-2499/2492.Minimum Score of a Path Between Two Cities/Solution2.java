class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] g = new List[n];
        boolean[] vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : roads) {
            int a = e[0] - 1, b = e[1] - 1, d = e[2];
            g[a].add(new int[] {b, d});
            g[b].add(new int[] {a, d});
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        vis[0] = true;
        int ans = 1 << 30;
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int i = q.pollFirst();
                for (var nxt : g[i]) {
                    int j = nxt[0], d = nxt[1];
                    ans = Math.min(ans, d);
                    if (!vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
            }
        }
        return ans;
    }
}