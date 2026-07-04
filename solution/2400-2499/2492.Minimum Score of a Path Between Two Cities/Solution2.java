class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (int[] e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].add(new int[] {b, w});
            g[b].add(new int[] {a, w});
        }

        boolean[] vis = new boolean[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        vis[1] = true;
        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int a = q.pollFirst();
                for (int[] nb : g[a]) {
                    int b = nb[0], w = nb[1];
                    ans = Math.min(ans, w);
                    if (!vis[b]) {
                        vis[b] = true;
                        q.offer(b);
                    }
                }
            }
        }
        return ans;
    }
}
