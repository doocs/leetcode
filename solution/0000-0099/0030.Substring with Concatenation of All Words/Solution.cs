public class Solution {
    public IList<int> FindSubstring(string s, string[] words) {
        var cnt = new Dictionary<string, int>();
        foreach (var w in words) {
            if (!cnt.ContainsKey(w)) {
                cnt[w] = 0;
            }
            ++cnt[w];
        }
        int m = s.Length, n = words.Length, k = words[0].Length;
        var ans = new List<int>();
        for (int i = 0; i < k; ++i) {
            var cnt1 = new Dictionary<string, int>();
            int l = i, r = i, t = 0;
            while (r + k <= m) {
                var w = s.Substring(r, k);
                r += k;
                if (!cnt.ContainsKey(w)) {
                    cnt1.Clear();
                    t = 0;
                    l = r;
                    continue;
                }
                if (!cnt1.ContainsKey(w)) {
                    cnt1[w] = 0;
                }
                ++cnt1[w];
                ++t;
                while (cnt1[w] > cnt[w]) {
                    --cnt1[s.Substring(l, k)];
                    l += k;
                    --t;
                }
                if (t == n) {
                    ans.Add(l);
                }
            }
        }
        return ans;
    }
}