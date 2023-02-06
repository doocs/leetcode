class Solution {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] d = new int[n + 1];
        for (var e : relations) {
            d[e[1]] |= 1 << e[0];
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        Set<Integer> vis = new HashSet<>();
        vis.add(0);
        while (!q.isEmpty()) {
            var p = q.pollFirst();
            int cur = p[0], t = p[1];
            if (cur == (1 << (n + 1)) - 2) {
                return t;
            }
            int nxt = 0;
            for (int i = 1; i <= n; ++i) {
                if ((cur & d[i]) == d[i]) {
                    nxt |= 1 << i;
                }
            }
            nxt ^= cur;
            if (Integer.bitCount(nxt) <= k) {
                if (vis.add(nxt | cur)) {
                    q.offer(new int[] {nxt | cur, t + 1});
                }
            } else {
                int x = nxt;
                while (nxt > 0) {
                    if (Integer.bitCount(nxt) == k && vis.add(nxt | cur)) {
                        q.offer(new int[] {nxt | cur, t + 1});
                    }
                    nxt = (nxt - 1) & x;
                }
            }
        }
        return 0;
    }
}