public class Solution {
    public bool WordPattern(string pattern, string s) {
        var ws = s.Split(' ');
        if (pattern.Length != ws.Length) {
            return false;
        }
        var d1 = new Dictionary<char, string>();
        var d2 = new Dictionary<string, char>();
        for (int i = 0; i < ws.Length; ++i) {
            var a = pattern[i];
            var b = ws[i];
            if (d1.ContainsKey(a) && d1[a] != b) {
                return false;
            }
            if (d2.ContainsKey(b) && d2[b] != a) {
                return false;
            }
            d1[a] = b;
            d2[b] = a;
        }
        return true;
    }
}