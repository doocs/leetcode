public class Solution {
    public IList<string> MaxNumOfSubstrings(string s) {
        int n = s.Length;
        int[] first = new int[26];
        int[] last = new int[26];
        Array.Fill(first, -1);
        for (int i = 0; i < n; ++i) {
            int x = s[i] - 'a';
            if (first[x] == -1) {
                first[x] = i;
            }
            last[x] = i;
        }
        List<int[]> segs = new List<int[]>();
        for (int x = 0; x < 26; ++x) {
            if (first[x] == -1) {
                continue;
            }
            int l = first[x], r = last[x];
            int i = l;
            for (; i <= r; ++i) {
                int y = s[i] - 'a';
                if (first[y] < l) {
                    break;
                }
                r = Math.Max(r, last[y]);
            }
            if (i > r) {
                segs.Add(new int[] { l, r });
            }
        }
        segs.Sort((a, b) => a[1] - b[1]);
        IList<string> ans = new List<string>();
        int end = -1;
        foreach (var e in segs) {
            int l = e[0], r = e[1];
            if (l > end) {
                ans.Add(s.Substring(l, r - l + 1));
                end = r;
            }
        }
        return ans;
    }
}
