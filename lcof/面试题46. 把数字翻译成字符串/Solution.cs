public class Solution {
    public int TranslateNum(int num) {
        return TranslateString(num.ToString());
    }

    private int TranslateString(string s) {
        if (s.Length < 2) {
            return 1;
        }
        int t = int.Parse(s[..2]);
        return t < 10 || t > 25 ? TranslateString(s[1..]) : TranslateString(s[1..]) + TranslateString(s[2..]);
    }
}