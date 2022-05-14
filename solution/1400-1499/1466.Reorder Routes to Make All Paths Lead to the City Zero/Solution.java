class Solution {
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Pair<Integer, Boolean>>> g = new HashMap<>();
        for (int[] e : connections) {
            int u = e[0], v = e[1];
            g.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair<>(v, true));
            g.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair<>(u, false));
        }
        boolean[] vis = new boolean[n];
        return dfs(0, g, vis);
    }

    private int dfs(int u, Map<Integer, List<Pair<Integer, Boolean>>> g, boolean[] vis) {
        vis[u] = true;
        int ans = 0;
        for (Pair<Integer, Boolean> e : g.getOrDefault(u, Collections.emptyList())) {
            int v = e.getKey();
            boolean exist = e.getValue();
            if (!vis[v]) {
                if (exist) {
                    ++ans;
                }
                ans += dfs(v, g, vis);
            }
        }
        return ans;
    }
}