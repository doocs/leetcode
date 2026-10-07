class Solution {
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : connections) {
            int a = e[0], b = e[1];
            g[a].add(new int[] {b, 1});
            g[b].add(new int[] {a, 0});
        }
        int ans = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int a = cur[0], fa = cur[1];
            for (var e : g[a]) {
                int b = e[0], c = e[1];
                if (b != fa) {
                    ans += c;
                    stk.push(new int[] {b, a});
                }
            }
        }
        return ans;
    }
}
