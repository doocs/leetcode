class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] e : graph) {
            g.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
        }
        Set<Integer> vis = new HashSet<>();
        vis.add(start);
        return dfs(start, target, g, vis);
    }

    private boolean dfs(int u, int target, Map<Integer, List<Integer>> g, Set<Integer> vis) {
        if (u == target) {
            return true;
        }
        for (int v : g.getOrDefault(u, Collections.emptyList())) {
            if (!vis.contains(v)) {
                vis.add(v);
                if (dfs(v, target, g, vis)) {
                    return true;
                }
            }
        }
        return false;
    }
}