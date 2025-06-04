public class Solution {
    public string SmallestEquivalentString(string s1, string s2, string baseStr) {
        int[] p = new int[26];
        for (int i = 0; i < 26; i++) {
            p[i] = i;
        }

        int Find(int x) {
            if (p[x] != x) {
                p[x] = Find(p[x]);
            }
            return p[x];
        }

        for (int i = 0; i < s1.Length; i++) {
            int x = s1[i] - 'a';
            int y = s2[i] - 'a';
            int px = Find(x);
            int py = Find(y);
            if (px < py) {
                p[py] = px;
            } else {
                p[px] = py;
            }
        }

        var res = new System.Text.StringBuilder();
        foreach (char c in baseStr) {
            int idx = Find(c - 'a');
            res.Append((char)(idx + 'a'));
        }

        return res.ToString();
    }
}
