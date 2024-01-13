public class Solution {
    public string ReplaceSpace(string s) {
        StringBuilder res = new StringBuilder();
        foreach (var c in s) {
            if (c == ' ') {
                res.Append("%20");
            } else {
                res.Append(c);
            }
        }
        return res.ToString();
    }
}
