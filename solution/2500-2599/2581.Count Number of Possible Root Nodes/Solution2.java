class Solution {
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Map<Long, Integer> gs = new HashMap<>();
        for (var e : guesses) {
            gs.merge(1L * e[0] * n + e[1], 1, Integer::sum);
        }
        int cnt = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1];
            for (int j : g[i]) {
                if (j != fa) {
                    cnt += gs.getOrDefault(1L * i * n + j, 0);
                    stk.push(new int[] {j, i});
                }
            }
        }
        int ans = 0;
        Deque<int[]> walk = new ArrayDeque<>();
        walk.push(new int[] {0, -1, cnt});
        while (!walk.isEmpty()) {
            int[] cur = walk.pop();
            int i = cur[0], fa = cur[1], c = cur[2];
            if (c >= k) {
                ++ans;
            }
            for (int j : g[i]) {
                if (j != fa) {
                    int a = gs.getOrDefault(1L * i * n + j, 0);
                    int b = gs.getOrDefault(1L * j * n + i, 0);
                    walk.push(new int[] {j, i, c - a + b});
                }
            }
        }
        return ans;
    }
}
