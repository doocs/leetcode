class Solution {
    private List<Integer>[] g;
    private List<Integer> arr = new ArrayList<>();
    private boolean[] vis;
    private int n;

    public int magnificentSets(int n, int[][] edges) {
        g = new List[n + 1];
        this.n = n;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        vis = new boolean[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (!vis[i]) {
                dfs(i);
                int t = -1;
                for (int v : arr) {
                    t = Math.max(t, bfs(v));
                }
                if (t == -1) {
                    return -1;
                }
                ans += t;
                arr.clear();
            }
        }
        return ans;
    }

    private int bfs(int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 1 << 30);
        dist[k] = 1;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(k);
        int ans = 1;
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : g[i]) {
                if (dist[j] == 1 << 30) {
                    dist[j] = dist[i] + 1;
                    ans = dist[j];
                    q.offer(j);
                }
            }
        }
        for (int i : arr) {
            if (dist[i] == 1 << 30) {
                dist[i] = ++ans;
            }
        }
        for (int i : arr) {
            for (int j : g[i]) {
                if (Math.abs(dist[i] - dist[j]) != 1) {
                    return -1;
                }
            }
        }
        return ans;
    }

    private void dfs(int i) {
        arr.add(i);
        vis[i] = true;
        for (int j : g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    }
}