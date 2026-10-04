class Solution {
    public long maxOutput(int n, int[][] edges, int[] price) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long[][] down = new long[n][2];
        long ans = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                long a = price[i], b = 0;
                for (int j : g[i]) {
                    if (j != fa) {
                        long c = down[j][0], d = down[j][1];
                        ans = Math.max(ans, Math.max(a + d, b + c));
                        a = Math.max(a, price[i] + c);
                        b = Math.max(b, price[i] + d);
                    }
                }
                down[i][0] = a;
                down[i][1] = b;
            }
        }
        return ans;
    }
}
