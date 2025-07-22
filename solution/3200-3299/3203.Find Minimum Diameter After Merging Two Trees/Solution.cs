public class Solution {
    private List<int>[] g;
    private int ans;
    private int a;

    public int MinimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = TreeDiameter(edges1);
        int d2 = TreeDiameter(edges2);
        return Math.Max(Math.Max(d1, d2), (d1 + 1) / 2 + (d2 + 1) / 2 + 1);
    }

    public int TreeDiameter(int[][] edges) {
        int n = edges.Length + 1;
        g = new List<int>[n];
        for (int k = 0; k < n; ++k) {
            g[k] = new List<int>();
        }
        ans = 0;
        a = 0;
        foreach (var e in edges) {
            int a = e[0], b = e[1];
            g[a].Add(b);
            g[b].Add(a);
        }
        Dfs(0, -1, 0);
        Dfs(a, -1, 0);
        return ans;
    }

    private void Dfs(int i, int fa, int t) {
        foreach (int j in g[i]) {
            if (j != fa) {
                Dfs(j, i, t + 1);
            }
        }
        if (ans < t) {
            ans = t;
            a = i;
        }
    }
}
