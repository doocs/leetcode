class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int dst = -1 ^ (-1 << n);

        Queue<Tuple> queue = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            queue.offer(new Tuple(i, 1 << i, 0));
            vis[i][1 << i] = true;
        }

        while (!queue.isEmpty()) {
            Tuple t = queue.poll();
            int u = t.u, state = t.state, dis = t.dis;
            for (int v : graph[u]) {
                int next = state | (1 << v);
                if (next == dst) {
                    return dis + 1;
                }
                if (!vis[v][next]) {
                    queue.offer(new Tuple(v, next, dis + 1));
                    vis[v][next] = true;
                }
            }
        }
        return 0;
    }

    private static class Tuple {
        int u;
        int state;
        int dis;

        public Tuple(int u, int state, int dis) {
            this.u = u;
            this.state = state;
            this.dis = dis;
        }
    }
}