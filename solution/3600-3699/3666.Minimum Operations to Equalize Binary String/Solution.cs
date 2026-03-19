public class Solution {
    public int MinOperations(string s, int k) {
        int n = s.Length;

        var ts = new SortedSet<int>[2];
        ts[0] = new SortedSet<int>();
        ts[1] = new SortedSet<int>();

        for (int i = 0; i <= n; i++) {
            ts[i % 2].Add(i);
        }

        int cnt0 = 0;
        foreach (char c in s) {
            if (c == '0') {
                cnt0++;
            }
        }

        ts[cnt0 % 2].Remove(cnt0);

        var q = new Queue<int>();
        q.Enqueue(cnt0);

        int ans = 0;

        while (q.Count > 0) {
            int size = q.Count;
            for (int i = 0; i < size; i++) {
                int cur = q.Dequeue();
                if (cur == 0) {
                    return ans;
                }

                int l = cur + k - 2 * Math.Min(cur, k);
                int r = cur + k - 2 * Math.Max(k - n + cur, 0);

                var t = ts[l % 2];

                var toRemove = new List<int>();
                foreach (int next in t.GetViewBetween(l, r)) {
                    q.Enqueue(next);
                    toRemove.Add(next);
                }

                foreach (int next in toRemove) {
                    t.Remove(next);
                }
            }
            ans++;
        }

        return -1;
    }
}
