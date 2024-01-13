class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] indeg = new int[n];
        List<Integer>[] rg = new List[n];
        Arrays.setAll(rg, k -> new ArrayList<>());
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            for (int j : graph[i]) {
                rg[j].add(i);
            }
            indeg[i] = graph[i].length;
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : rg[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}