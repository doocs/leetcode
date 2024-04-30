public class Solution {
    public string CapitalizeTitle(string title) {
        List<string> ans = new List<string>();
        foreach (string s in title.Split(' ')) {
            if (s.Length < 3) {
                ans.Add(s.ToLower());
            } else {
                ans.Add(char.ToUpper(s[0]) + s.Substring(1).ToLower());
            }
        }
        return string.Join(" ", ans);
    }
}