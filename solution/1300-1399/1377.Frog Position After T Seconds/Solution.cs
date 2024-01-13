public class Solution {
    public double FrogPosition(int n, int[][] edges, int t, int target) {
        List<int>[] g = new List<int>[n + 1];
        for (int i = 0; i < n + 1; i++) {
            g[i] = new List<int>();
        }
        foreach (int[] e in edges) {
            int u = e[0], v = e[1];
            g[u].Add(v);
            g[v].Add(u);
        }
        Queue<Tuple<int, double>> q = new Queue<Tuple<int, double>>();
        q.Enqueue(new Tuple<int, double>(1, 1.0));
        bool[] vis = new bool[n + 1];
        vis[1] = true;
        for (; q.Count > 0 && t >= 0; --t) {
            for (int k = q.Count; k > 0; --k) {
                (var u, var p) = q.Dequeue();
                int cnt = g[u].Count - (u == 1 ? 0 : 1);
                if (u == target) {
                    return cnt * t == 0 ? p : 0;
                }
                foreach (int v in g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.Enqueue(new Tuple<int, double>(v, p / cnt));
                    }
                }
            }
        }
        return 0;
    }
}
