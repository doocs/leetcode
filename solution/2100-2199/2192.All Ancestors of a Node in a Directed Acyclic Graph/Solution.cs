public class Solution {
    private int n;
    private List<int>[] g;
    private IList<IList<int>> ans;

    public IList<IList<int>> GetAncestors(int n, int[][] edges) {
        g = new List<int>[n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            g[i] = new List<int>();
        }
        foreach (var e in edges) {
            g[e[0]].Add(e[1]);
        }
        ans = new List<IList<int>>();
        for (int i = 0; i < n; ++i) {
            ans.Add(new List<int>());
        }
        for (int i = 0; i < n; ++i) {
            BFS(i);
        }
        return ans;
    }

    private void BFS(int s) {
        Queue<int> q = new Queue<int>();
        q.Enqueue(s);
        bool[] vis = new bool[n];
        vis[s] = true;
        while (q.Count > 0) {
            int i = q.Dequeue();
            foreach (int j in g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.Enqueue(j);
                    ans[j].Add(s);
                }
            }
        }
    }
}