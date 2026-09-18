public class Solution {
    public int[] FindMedian(int n, int[][] edges, int[][] queries) {
        int m = 32 - BitOperations.LeadingZeroCount((uint)n);
        List<int[]>[] g = new List<int[]>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int[]>();
        }
        foreach (var e in edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].Add(new int[] { v, w });
            g[v].Add(new int[] { u, w });
        }
        int[][] f = new int[n][];
        for (int i = 0; i < n; ++i) {
            f[i] = new int[m];
        }
        int[] p = new int[n];
        int[] depth = new int[n];
        long[] dist = new long[n];
        Queue<int> q = new Queue<int>();
        q.Enqueue(0);
        while (q.Count > 0) {
            int i = q.Dequeue();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            foreach (var nxt in g[i]) {
                int j = nxt[0], w = nxt[1];
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    dist[j] = dist[i] + w;
                    q.Enqueue(j);
                }
            }
        }
        int[] ans = new int[queries.Length];
        for (int i = 0; i < queries.Length; ++i) {
            int u = queries[i][0], v = queries[i][1];
            if (u == v) {
                ans[i] = u;
                continue;
            }
            int x = u, y = v;
            if (depth[x] < depth[y]) {
                int t = x;
                x = y;
                y = t;
            }
            for (int j = m - 1; j >= 0; --j) {
                if (depth[x] - depth[y] >= (1 << j)) {
                    x = f[x][j];
                }
            }
            for (int j = m - 1; j >= 0; --j) {
                if (f[x][j] != f[y][j]) {
                    x = f[x][j];
                    y = f[y][j];
                }
            }
            if (x != y) {
                x = p[x];
            }
            long w = dist[u] + dist[v] - 2 * dist[x];
            if (2 * (dist[u] - dist[x]) >= w) {
                int cur = u;
                for (int j = m - 1; j >= 0; --j) {
                    int k = f[cur][j];
                    if (depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w) {
                        cur = k;
                    }
                }
                ans[i] = p[cur];
            } else {
                int cur = v;
                for (int j = m - 1; j >= 0; --j) {
                    int k = f[cur][j];
                    if (depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w) {
                        cur = k;
                    }
                }
                ans[i] = cur;
            }
        }
        return ans;
    }
}
