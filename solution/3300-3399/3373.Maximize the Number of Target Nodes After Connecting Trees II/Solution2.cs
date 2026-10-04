public class Solution {
    public int[] MaxTargetNodes(int[][] edges1, int[][] edges2) {
        var g1 = Build(edges1);
        var g2 = Build(edges2);
        int n = g1.Length, m = g2.Length;
        var c1 = new int[n];
        var c2 = new int[m];
        var cnt1 = new int[2];
        var cnt2 = new int[2];

        Dfs(g2, c2, cnt2);
        Dfs(g1, c1, cnt1);

        int t = Math.Max(cnt2[0], cnt2[1]);
        var ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = t + cnt1[c1[i]];
        }
        return ans;
    }

    private List<int>[] Build(int[][] edges) {
        int n = edges.Length + 1;
        var g = new List<int>[n];
        for (int i = 0; i < n; i++) {
            g[i] = new List<int>();
        }
        foreach (var e in edges) {
            int a = e[0], b = e[1];
            g[a].Add(b);
            g[b].Add(a);
        }
        return g;
    }

    private void Dfs(List<int>[] g, int[] c, int[] cnt) {
        var stk = new Stack<int[]>();
        stk.Push(new int[] { 0, -1, 0 });
        while (stk.Count > 0) {
            int[] cur = stk.Pop();
            int a = cur[0], fa = cur[1], d = cur[2];
            c[a] = d;
            cnt[d]++;
            foreach (var b in g[a]) {
                if (b != fa) {
                    stk.Push(new int[] { b, a, d ^ 1 });
                }
            }
        }
    }
}
