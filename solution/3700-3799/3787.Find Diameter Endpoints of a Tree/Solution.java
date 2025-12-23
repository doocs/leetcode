class Solution {
    public String findSpecialNodes(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        record BFSResult(int far, int[] dist) {
        }

        IntFunction<BFSResult> bfs = (int start) -> {
            int[] dist = new int[n];
            Arrays.fill(dist, -1);
            dist[start] = 0;
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(start);
            int far = start;
            while (!q.isEmpty()) {
                int u = q.poll();
                if (dist[u] > dist[far]) {
                    far = u;
                }
                for (int v : g[u]) {
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        q.add(v);
                    }
                }
            }
            return new BFSResult(far, dist);
        };

        int a = bfs.apply(0).far();
        BFSResult r1 = bfs.apply(a);
        int b = r1.far();
        int[] dist1 = r1.dist();
        int[] dist2 = bfs.apply(b).dist();
        int d = dist1[b];

        char[] ans = new char[n];
        Arrays.fill(ans, '0');
        for (int i = 0; i < n; i++) {
            if (dist1[i] == d || dist2[i] == d) {
                ans[i] = '1';
            }
        }
        return new String(ans);
    }
}
