public class Solution {
    public IList<string> TwoEditWords(string[] queries, string[] dictionary) {
        var ans = new List<string>();
        foreach (var s in queries) {
            foreach (var t in dictionary) {
                int cnt = 0;
                for (int i = 0; i < s.Length; i++) {
                    if (s[i] != t[i]) {
                        cnt++;
                    }
                }
                if (cnt < 3) {
                    ans.Add(s);
                    break;
                }
            }
        }
        return ans;
    }
}