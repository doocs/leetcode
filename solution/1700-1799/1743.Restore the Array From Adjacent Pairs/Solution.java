class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            graph.putIfAbsent(pair[0], new ArrayList<>());
            graph.putIfAbsent(pair[1], new ArrayList<>());
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
        List<Integer> ans = new ArrayList<>();
        Set<Integer> vis = new HashSet<>();
        int start = -1;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }
        dfs(graph, ans, vis, start);
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void dfs(Map<Integer, List<Integer>> graph, List<Integer> ans, Set<Integer> vis, int idx) {
        if (vis.contains(idx)) {
            return;
        }
        vis.add(idx);
        ans.add(idx);
        for (Integer next : graph.get(idx)) {
            dfs(graph, ans, vis, next);
        }
    }
}
