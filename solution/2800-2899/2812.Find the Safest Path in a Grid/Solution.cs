public class Solution {
    public int MaximumSafenessFactor(IList<IList<int>> grid) {
        int n = grid.Count;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return 0;
        }

        int inf = 1 << 30;
        int[,] dist = new int[n, n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i, j] = inf;
            }
        }

        Queue<(int x, int y)> q = new Queue<(int x, int y)>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dist[i, j] = 0;
                    q.Enqueue((i, j));
                }
            }
        }

        int[] dirs = new int[] { -1, 0, 1, 0, -1 };

        while (q.Count > 0) {
            var (i, j) = q.Dequeue();

            for (int k = 0; k < 4; k++) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];

                if (x >= 0 && x < n && y >= 0 && y < n && dist[x, y] == inf) {
                    dist[x, y] = dist[i, j] + 1;
                    q.Enqueue((x, y));
                }
            }
        }

        List<(int d, int i, int j)> t = new List<(int d, int i, int j)>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t.Add((dist[i, j], i, j));
            }
        }

        t.Sort((a, b) => b.d.CompareTo(a.d));

        UnionFind uf = new UnionFind(n * n);

        foreach (var (d, i, j) in t) {
            for (int k = 0; k < 4; k++) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];

                if (x >= 0 && x < n && y >= 0 && y < n && dist[x, y] >= d) {
                    uf.Union(i * n + j, x * n + y);
                }
            }

            if (uf.Find(0) == uf.Find(n * n - 1)) {
                return d;
            }
        }

        return 0;
    }
}

public class UnionFind {
    public int[] p;

    public UnionFind(int n) {
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
    }

    public int Find(int x) {
        if (p[x] != x) {
            p[x] = Find(p[x]);
        }
        return p[x];
    }

    public bool Union(int a, int b) {
        int pa = Find(a);
        int pb = Find(b);
        if (pa == pb) {
            return false;
        }
        p[pa] = pb;
        return true;
    }
}