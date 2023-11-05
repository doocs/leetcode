public class Solution {
    public IList<string> FindRepeatedDnaSequences(string s) {
        var cnt = new Dictionary<string, int>();
        var ans = new List<string>();
        for (int i = 0; i < s.Length - 10 + 1; ++i) {
            var t = s.Substring(i, 10);
            if (!cnt.ContainsKey(t)) {
                cnt[t] = 0;
            }
            if (++cnt[t] == 2) {
                ans.Add(t);
            }
        }
        return ans;
    }
}