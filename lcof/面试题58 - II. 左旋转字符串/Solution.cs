public class Solution {
    public string ReverseLeftWords(string s, int n) {
        return s.Substring(n) + s.Substring(0, n);
    }
}