public class Solution {
    public bool CanConstruct(string ransomNote, string magazine) {
        int[] cnt = new int[26];
        foreach (var c in magazine) {
            ++cnt[c - 'a'];
        }
        foreach (var c in ransomNote) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}