public class Solution {
    public char FirstUniqChar(string s) {
        Dictionary<char, bool> dic = new Dictionary<char, bool>();
        foreach (var c in s) {
            if (dic.ContainsKey(c)) {
                dic[c] = false;
            }
            else {
                dic.Add(c, true);
            }
        }
        foreach (var d in dic) {
            if (d.Value) {
                return d.Key;
            }
        }
        return ' ';
    }
}