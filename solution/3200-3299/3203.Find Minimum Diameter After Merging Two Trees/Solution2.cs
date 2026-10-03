public class Solution {
    public int MinimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = TreeDiameter(edges1);
        int d2 = TreeDiameter(edges2);
        return Math.Max(Math.Max(d1, d2), (d1 + 1) / 2 + (d2 + 1) / 2 + 1);
    }

    public int TreeDiameter(int[][] edges) {
        int n = edges.Length + 1;
        List<int>[] g = new List<int>[n];
        for (int k = 0; k < n; ++k) {
            g[k] = new List<int>();
        }
        foreach (var e in edges) {
            int u = e[0], v = e[1];
            g[u].Add(v);
            g[v].Add(u);
        }
        int[] p = Farthest(g, 0);
        p = Farthest(g, p[1]);
        return p[0];
    }

    private int[] Farthest(List<int>[] g, int start) {
        int ans = 0, node = start;
        Stack<int[]> stk = new Stack<int[]>();
        stk.Push(new int[] { start, -1, 0 });
        while (stk.Count > 0) {
            int[] cur = stk.Pop();
            int i = cur[0], fa = cur[1], t = cur[2];
            if (ans < t) {
                ans = t;
                node = i;
            }
            foreach (int j in g[i]) {
                if (j != fa) {
                    stk.Push(new int[] { j, i, t + 1 });
                }
            }
        }
        return new int[] { ans, node };
    }
}
