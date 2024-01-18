public class Solution {
    public char FirstUniqChar(string s) {
        var cnt = new int[26];
        foreach(var c in s) {
            cnt[c - 'a'] ++;
        }
        foreach(var c in s) {
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
