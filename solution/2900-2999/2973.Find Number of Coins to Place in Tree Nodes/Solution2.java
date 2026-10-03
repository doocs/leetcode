class Solution {
    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long[] ans = new long[n];
        Arrays.fill(ans, 1);
        List<Integer>[] sub = new List[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int a = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {a, fa, 1});
                for (int b : g[a]) {
                    if (b != fa) {
                        stk.push(new int[] {b, a, 0});
                    }
                }
            } else {
                List<Integer> res = new ArrayList<>();
                res.add(cost[a]);
                for (int b : g[a]) {
                    if (b != fa) {
                        res.addAll(sub[b]);
                    }
                }
                Collections.sort(res);
                int m = res.size();
                if (m >= 3) {
                    long x = (long) res.get(m - 1) * res.get(m - 2) * res.get(m - 3);
                    long y = (long) res.get(0) * res.get(1) * res.get(m - 1);
                    ans[a] = Math.max(0, Math.max(x, y));
                }
                if (m > 5) {
                    res = new ArrayList<>(List.of(
                        res.get(0), res.get(1), res.get(m - 3), res.get(m - 2), res.get(m - 1)));
                }
                sub[a] = res;
            }
        }
        return ans;
    }
}
