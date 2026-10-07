class Solution {
    public int maximumSubtreeSize(int[][] edges, int[] colors) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[] size = new int[n];
        Arrays.fill(size, 1);
        boolean[] ok = new boolean[n];
        int ans = 0;
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
                boolean good = true;
                for (int b : g[a]) {
                    if (b != fa) {
                        good = good && colors[a] == colors[b] && ok[b];
                        size[a] += size[b];
                    }
                }
                if (good) {
                    ans = Math.max(ans, size[a]);
                }
                ok[a] = good;
            }
        }
        return ans;
    }
}
