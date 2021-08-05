class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] outDegrees = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        List<List<Integer>> revGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            revGraph.add(new ArrayList<>());
        }
        for (int u = 0; u < n; u++) {
            for (int v : graph[u]) {
                revGraph.get(v).add(u);
            }
            outDegrees[u] = graph[u].length;
            if (outDegrees[u] == 0) {
                queue.offer(u);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : revGraph.get(v)) {
                if (--outDegrees[u] == 0) {
                    queue.offer(u);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (outDegrees[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}