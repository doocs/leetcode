class Solution {
    private static final int N = 6010;

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> s = new HashSet<>();
        for (int v : forbidden) {
            s.add(v);
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 2});
        boolean[][] vis = new boolean[N][2];
        vis[0][0] = true;
        vis[0][1] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int[] p = q.poll();
                int i = p[0], dir = p[1];
                if (i == x) {
                    return ans;
                }
                List<int[]> nxt = new ArrayList<>();
                nxt.add(new int[]{i + a, 1});
                if (dir != 0) {
                    nxt.add(new int[]{i - b, 0});
                }
                for (int[] e : nxt) {
                    int j = e[0];
                    dir = e[1];
                    if (j >= 0 && j < N && !s.contains(j) && !vis[j][dir]) {
                        vis[j][dir] = true;
                        q.offer(new int[]{j, dir});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}