class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0];
            int v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int d = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {1, 0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], dep = cur[2];
            d = Math.max(d, dep);
            for (int j : g[i]) {
                if (j != fa) {
                    stk.push(new int[] {j, i, dep + 1});
                }
            }
        }
        return (int) pow(2, d - 1, 1_000_000_007);
    }

    private long pow(long a, int n, int mod) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }
}
