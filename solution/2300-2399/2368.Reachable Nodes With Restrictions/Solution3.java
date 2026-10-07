class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        boolean[] vis = new boolean[n];
        for (int i : restricted) {
            vis[i] = true;
        }
        int ans = 0;
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(0);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++ans;
            for (int j : g[i]) {
                if (!vis[j]) {
                    stk.push(j);
                }
            }
        }
        return ans;
    }
}
