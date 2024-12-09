class Solution {
    private int[] vis;
    private List<Integer>[] g;
    private List<Integer> seq = new ArrayList<>();

    public int minRunesToAdd(int n, int[] crystals, int[] flowFrom, int[] flowTo) {
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < flowFrom.length; ++i) {
            g[flowFrom[i]].add(flowTo[i]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        vis = new int[n];
        for (int i : crystals) {
            vis[i] = 1;
            q.offer(i);
        }
        bfs(q);
        for (int i = 0; i < n; ++i) {
            if (vis[i] == 0) {
                dfs(i);
            }
        }
        int ans = 0;
        for (int i = seq.size() - 1; i >= 0; --i) {
            int a = seq.get(i);
            if (vis[a] == 2) {
                vis[a] = 1;
                q.clear();
                q.offer(a);
                bfs(q);
                ++ans;
            }
        }
        return ans;
    }

    private void bfs(Deque<Integer> q) {
        while (!q.isEmpty()) {
            int a = q.poll();
            for (int b : g[a]) {
                if (vis[b] == 1) {
                    continue;
                }
                vis[b] = 1;
                q.offer(b);
            }
        }
    }

    private void dfs(int a) {
        vis[a] = 2;
        for (int b : g[a]) {
            if (vis[b] > 0) {
                continue;
            }
            dfs(b);
        }
        seq.add(a);
    }
}
