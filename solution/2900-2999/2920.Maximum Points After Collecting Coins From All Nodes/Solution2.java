class Solution {
    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[][] f = new int[n][15];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int c : g[i]) {
                    if (c != fa) {
                        stk.push(new int[] {c, i, 0});
                    }
                }
            } else {
                for (int j = 0; j < 15; ++j) {
                    int a = (coins[i] >> j) - k;
                    int b = coins[i] >> (j + 1);
                    for (int c : g[i]) {
                        if (c != fa) {
                            a += f[c][j];
                            if (j < 14) {
                                b += f[c][j + 1];
                            }
                        }
                    }
                    f[i][j] = Math.max(a, b);
                }
            }
        }
        return f[0][0];
    }
}
