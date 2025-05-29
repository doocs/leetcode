public class Solution {
    public int ClosestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.Length;
        List<int>[] g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
            if (edges[i] != -1) {
                g[i].Add(edges[i]);
            }
        }
        int inf = 1 << 30;
        int[] f(int i) {
            int[] dist = new int[n];
            Array.Fill(dist, inf);
            dist[i] = 0;
            Queue<int> q = new Queue<int>();
            q.Enqueue(i);
            while (q.Count > 0) {
                i = q.Dequeue();
                foreach (int j in g[i]) {
                    if (dist[j] == inf) {
                        dist[j] = dist[i] + 1;
                        q.Enqueue(j);
                    }
                }
            }
            return dist;
        }
        int[] d1 = f(node1);
        int[] d2 = f(node2);
        int ans = -1, d = inf;
        for (int i = 0; i < n; ++i) {
            int t = Math.Max(d1[i], d2[i]);
            if (t < d) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }
}
