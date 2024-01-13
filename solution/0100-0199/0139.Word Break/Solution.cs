public class Solution {
    public bool WordBreak(string s, IList<string> wordDict) {
        var words = new HashSet<string>(wordDict);
        int n = s.Length;
        var f = new bool[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (f[j] && words.Contains(s.Substring(j, i - j))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
