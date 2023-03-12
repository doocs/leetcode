public class Solution {
    public string[] FindWords(string[] words) {
        string s = "12210111011122000010020202";
        IList<string> ans = new List<string>();
        foreach (string w in words) {
            char x = s[char.ToLower(w[0]) - 'a'];
            bool ok = true;
            for (int i = 1; i < w.Length; ++i) {
                if (s[char.ToLower(w[i]) - 'a'] != x) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.Add(w);
            }
        }
        return ans.ToArray();
    }
}