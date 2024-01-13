class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        boolean[] vis = new boolean[n];
        for (int v : restricted) {
            vis[v] = true;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int ans = 0;
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            ++ans;
            vis[i] = true;
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }
}