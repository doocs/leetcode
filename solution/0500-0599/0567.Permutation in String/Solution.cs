public class Solution {
    public bool CheckInclusion(string s1, string s2) {
        int need = 0;
        int[] cnt = new int[26];

        foreach (char c in s1) {
            if (++cnt[c - 'a'] == 1) {
                need++;
            }
        }

        int m = s1.Length, n = s2.Length;
        for (int i = 0; i < n; i++) {
            int c = s2[i] - 'a';
            if (--cnt[c] == 0) {
                need--;
            }

            if (i >= m) {
                c = s2[i - m] - 'a';
                if (++cnt[c] == 1) {
                    need++;
                }
            }

            if (need == 0) {
                return true;
            }
        }
        return false;
    }
}
