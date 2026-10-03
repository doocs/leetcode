class Solution {
    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int ans = 0;
        int[] sz = new int[n];
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
                int pre = -1, cnt = 1, ok = 1;
                for (int b : g[a]) {
                    if (b != fa) {
                        int curSz = sz[b];
                        cnt += curSz;
                        if (pre < 0) {
                            pre = curSz;
                        } else if (pre != curSz) {
                            ok = 0;
                        }
                    }
                }
                ans += ok;
                sz[a] = cnt;
            }
        }
        return ans;
    }
}
