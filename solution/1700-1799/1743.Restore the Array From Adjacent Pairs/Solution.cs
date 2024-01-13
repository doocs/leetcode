public class Solution {
    public int[] RestoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.Length + 1;
        Dictionary<int, List<int>> g = new Dictionary<int, List<int>>();

        foreach (int[] e in adjacentPairs) {
            int a = e[0], b = e[1];
            if (!g.ContainsKey(a)) {
                g[a] = new List<int>();
            }
            if (!g.ContainsKey(b)) {
                g[b] = new List<int>();
            }
            g[a].Add(b);
            g[b].Add(a);
        }

        int[] ans = new int[n];

        foreach (var entry in g) {
            if (entry.Value.Count == 1) {
                ans[0] = entry.Key;
                ans[1] = entry.Value[0];
                break;
            }
        }

        for (int i = 2; i < n; ++i) {
            List<int> v = g[ans[i - 1]];
            ans[i] = v[1] == ans[i - 2] ? v[0] : v[1];
        }

        return ans;
    }
}
