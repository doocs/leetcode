public class Solution {
    public int MaxFreqSum(string s) {
        int[] cnt = new int[26];
        foreach (char c in s) {
            cnt[c - 'a']++;
        }
        int a = 0, b = 0;
        for (int i = 0; i < 26; i++) {
            char c = (char)('a' + i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                a = Math.Max(a, cnt[i]);
            } else {
                b = Math.Max(b, cnt[i]);
            }
        }
        return a + b;
    }
}
