class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int need = 0;
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            if (++cnt[c - 'a'] == 1) {
                ++need;
            }
        }
        int m = s1.length(), n = s2.length();
        for (int i = 0; i < n; ++i) {
            int c = s2.charAt(i) - 'a';
            if (--cnt[c] == 0) {
                --need;
            }
            if (i >= m) {
                c = s2.charAt(i - m) - 'a';
                if (++cnt[c] == 1) {
                    ++need;
                }
            }
            if (need == 0) {
                return true;
            }
        }
        return false;
    }
}
