class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(source);
        boolean[] vis = new boolean[n];
        vis[source] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (i == destination) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.offer(j);
                }
            }
        }
        return false;
    }
}
