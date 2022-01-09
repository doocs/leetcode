class Solution {
    private Map<Integer, List<Integer>> g;
    private int[] counter;
    private int[] value;

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        g = new HashMap<>();
        for (int i = 0; i < nodes; ++i) {
            if (parent[i] != -1) {
                g.computeIfAbsent(parent[i], k -> new ArrayList<>()).add(i);
            }
        }
        this.value = value;
        counter = new int[nodes];
        Arrays.fill(counter, 1);
        dfs(0);
        return counter[0];
    }

    private void dfs(int u) {
        for (int v : g.getOrDefault(u, Collections.emptyList())) {
            dfs(v);
            value[u] += value[v];
            counter[u] += counter[v];
        }
        if (value[u] == 0) {
            counter[u] = 0;
        }
    }
}