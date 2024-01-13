class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] e : graph) {
            g.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        Set<Integer> vis = new HashSet<>();
        vis.add(start);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == target) {
                return true;
            }
            for (int v : g.getOrDefault(u, Collections.emptyList())) {
                if (!vis.contains(v)) {
                    vis.add(v);
                    q.offer(v);
                }
            }
        }
        return false;
    }
}