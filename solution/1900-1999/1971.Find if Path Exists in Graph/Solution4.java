class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        boolean[] vis = new boolean[n];
        vis[source] = true;
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(source);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            for (int j : g[i]) {
                if (j == destination) {
                    return true;
                }
                if (!vis[j]) {
                    vis[j] = true;
                    stk.push(j);
                }
            }
        }
        return false;
    }
}
