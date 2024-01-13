class Solution {
    private List<Integer>[] g;
    private int msk;
    private int nxt;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u].add(v);
            g[v].add(u);
        }
        int[] ans = new int[n - 1];
        for (int mask = 1; mask < 1 << n; ++mask) {
            if ((mask & (mask - 1)) == 0) {
                continue;
            }
            msk = mask;
            int cur = 31 - Integer.numberOfLeadingZeros(msk);
            bfs(cur);
            if (msk == 0) {
                msk = mask;
                int mx = bfs(nxt);
                ++ans[mx - 1];
            }
        }
        return ans;
    }

    private int bfs(int u) {
        int d = -1;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(u);
        msk ^= 1 << u;
        while (!q.isEmpty()) {
            ++d;
            for (int k = q.size(); k > 0; --k) {
                u = q.poll();
                nxt = u;
                for (int v : g[u]) {
                    if ((msk >> v & 1) == 1) {
                        msk ^= 1 << v;
                        q.offer(v);
                    }
                }
            }
        }
        return d;
    }
}