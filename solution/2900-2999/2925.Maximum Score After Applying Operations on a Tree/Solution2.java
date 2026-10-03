class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long[] sum = new long[n];
        long[] best = new long[n];
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
                long a = 0, b = 0;
                boolean leaf = true;
                for (int j : g[i]) {
                    if (j != fa) {
                        leaf = false;
                        a += sum[j];
                        b += best[j];
                    }
                }
                if (leaf) {
                    sum[i] = values[i];
                    best[i] = 0;
                } else {
                    sum[i] = values[i] + a;
                    best[i] = Math.max(values[i] + b, a);
                }
            }
        }
        return best[0];
    }
}
