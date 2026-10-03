class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].add(new int[] {b, w});
            g[b].add(new int[] {a, w});
        }
        int ans = Integer.MAX_VALUE;
        boolean[] vis = new boolean[n + 1];
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(1);
        while (!stk.isEmpty()) {
            int a = stk.pop();
            if (vis[a]) {
                continue;
            }
            vis[a] = true;
            for (int[] nb : g[a]) {
                int b = nb[0], w = nb[1];
                ans = Math.min(ans, w);
                if (!vis[b]) {
                    stk.push(b);
                }
            }
        }
        return ans;
    }
}
