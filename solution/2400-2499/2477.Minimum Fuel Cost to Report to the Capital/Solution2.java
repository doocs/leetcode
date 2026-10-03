class Solution {
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : roads) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long ans = 0;
        int[] sz = new int[n];
        Arrays.fill(sz, 1);
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
                for (int b : g[a]) {
                    if (b != fa) {
                        int t = sz[b];
                        ans += (t + seats - 1) / seats;
                        sz[a] += t;
                    }
                }
            }
        }
        return ans;
    }
}
