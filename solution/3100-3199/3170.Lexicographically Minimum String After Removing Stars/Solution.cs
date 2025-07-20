public class Solution {
    public string ClearStars(string s) {
        int n = s.Length;
        List<int>[] g = new List<int>[26];
        for (int i = 0; i < 26; i++) {
            g[i] = new List<int>();
        }

        bool[] rem = new bool[n];
        for (int i = 0; i < n; i++) {
            char ch = s[i];
            if (ch == '*') {
                rem[i] = true;
                for (int j = 0; j < 26; j++) {
                    if (g[j].Count > 0) {
                        int idx = g[j][g[j].Count - 1];
                        g[j].RemoveAt(g[j].Count - 1);
                        rem[idx] = true;
                        break;
                    }
                }
            } else {
                g[ch - 'a'].Add(i);
            }
        }

        var ans = new System.Text.StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!rem[i]) {
                ans.Append(s[i]);
            }
        }

        return ans.ToString();
    }
}
