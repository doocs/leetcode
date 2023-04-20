public class Solution {
    public IList<string> FullJustify(string[] words, int maxWidth) {
        var ans = new List<string>();
        for (int i = 0, n = words.Length; i < n;) {
            var t = new List<string>();
            t.Add(words[i]);
            int cnt = words[i].Length;
            ++i;
            while (i < n && cnt + 1 + words[i].Length <= maxWidth) {
                t.Add(words[i]);
                cnt += 1 + words[i].Length;
                ++i;
            }
            if (i == n || t.Count == 1) {
                string left = string.Join(" ", t);
                string right = new string(' ', maxWidth - left.Length);
                ans.Add(left + right);
                continue;
            }
            int spaceWidth = maxWidth - (cnt - t.Count + 1);
            int w = spaceWidth / (t.Count - 1);
            int m = spaceWidth % (t.Count - 1);
            var row = new StringBuilder();
            for (int j = 0; j < t.Count - 1; ++j) {
                row.Append(t[j]);
                row.Append(new string(' ', w + (j < m ? 1 : 0)));
            }
            row.Append(t[t.Count - 1]);
            ans.Add(row.ToString());
        }
        return ans;
    }
}