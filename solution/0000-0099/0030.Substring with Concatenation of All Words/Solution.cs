public class Solution {
    public IList<int> FindSubstring(string s, string[] words) {
        var cnt = new Dictionary<string, int>();
        foreach (var w in words) {
            if (cnt.ContainsKey(w)) {
                cnt[w]++;
            } else {
                cnt[w] = 1;
            }
        }

        var ans = new List<int>();
        int m = s.Length, n = words.Length, k = words[0].Length;

        for (int i = 0; i < k; ++i) {
            int l = i, r = i;
            var cnt1 = new Dictionary<string, int>();
            while (r + k <= m) {
                var t = s.Substring(r, k);
                r += k;

                if (!cnt.ContainsKey(t)) {
                    cnt1.Clear();
                    l = r;
                    continue;
                }

                if (cnt1.ContainsKey(t)) {
                    cnt1[t]++;
                } else {
                    cnt1[t] = 1;
                }

                while (cnt1[t] > cnt[t]) {
                    var w = s.Substring(l, k);
                    cnt1[w]--;
                    if (cnt1[w] == 0) {
                        cnt1.Remove(w);
                    }
                    l += k;
                }

                if (r - l == n * k) {
                    ans.Add(l);
                }
            }
        }

        return ans;
    }
}
