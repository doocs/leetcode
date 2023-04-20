public class Solution {
    public bool CheckAlmostEquivalent(string word1, string word2) {
        int[] cnt = new int[26];
        foreach (var c in word1) {
            cnt[c - 'a']++;
        }
        foreach (var c in word2) {
            cnt[c - 'a']--;
        }
        return cnt.All(x => Math.Abs(x) <= 3);
    }
}