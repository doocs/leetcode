class Solution {
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int node = farthest(g, 0)[0];
        return farthest(g, node)[1];
    }

    private int[] farthest(List<Integer>[] g, int start) {
        int ans = 0, node = start;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {start, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], t = cur[2];
            if (ans < t) {
                ans = t;
                node = i;
            }
            for (int j : g[i]) {
                if (j != fa) {
                    stk.push(new int[] {j, i, t + 1});
                }
            }
        }
        return new int[] {node, ans};
    }
}
