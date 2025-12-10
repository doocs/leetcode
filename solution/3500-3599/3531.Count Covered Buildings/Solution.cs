public class Solution {
    public int CountCoveredBuildings(int n, int[][] buildings) {
        var g1 = new Dictionary<int, List<int>>();
        var g2 = new Dictionary<int, List<int>>();

        foreach (var b in buildings) {
            int x = b[0], y = b[1];

            if (!g1.ContainsKey(x)) {
                g1[x] = new List<int>();
            }
            g1[x].Add(y);

            if (!g2.ContainsKey(y)) {
                g2[y] = new List<int>();
            }
            g2[y].Add(x);
        }

        foreach (var kv in g1) {
            kv.Value.Sort();
        }
        foreach (var kv in g2) {
            kv.Value.Sort();
        }

        int ans = 0;

        foreach (var b in buildings) {
            int x = b[0], y = b[1];
            var l1 = g1[x];
            var l2 = g2[y];

            if (l2[0] < x && x < l2[l2.Count - 1] &&
                l1[0] < y && y < l1[l1.Count - 1]) {
                ans++;
            }
        }

        return ans;
    }
}
