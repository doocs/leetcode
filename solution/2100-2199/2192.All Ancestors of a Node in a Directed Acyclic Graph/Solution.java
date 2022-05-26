class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[1]].add(e[0]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<Integer> t = new ArrayList<>();
            if (g[i].isEmpty()) {
                ans.add(t);
                continue;
            }
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(i);
            boolean[] vis = new boolean[n];
            vis[i] = true;
            while (!q.isEmpty()) {
                for (int j = q.size(); j > 0; --j) {
                    int v = q.poll();
                    for (int u : g[v]) {
                        if (!vis[u]) {
                            vis[u] = true;
                            q.offer(u);
                            t.add(u);
                        }
                    }
                }
            }
            Collections.sort(t);
            ans.add(t);
        }
        return ans;
    }
}