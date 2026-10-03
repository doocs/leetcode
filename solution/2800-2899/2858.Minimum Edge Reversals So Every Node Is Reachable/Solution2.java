class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(new int[] {y, 1});
            g[y].add(new int[] {x, -1});
        }
        int[] ans = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1];
            for (var ne : g[i]) {
                int j = ne[0], k = ne[1];
                if (j != fa) {
                    ans[0] += k < 0 ? 1 : 0;
                    stk.push(new int[] {j, i});
                }
            }
        }
        stk.push(new int[] {0, -1});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1];
            for (var ne : g[i]) {
                int j = ne[0], k = ne[1];
                if (j != fa) {
                    ans[j] = ans[i] + k;
                    stk.push(new int[] {j, i});
                }
            }
        }
        return ans;
    }
}
