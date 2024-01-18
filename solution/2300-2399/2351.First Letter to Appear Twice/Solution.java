class Solution {
    public char repeatedCharacter(String s) {
        int[] cnt = new int[26];
        for (int i = 0;; ++i) {
            char c = s.charAt(i);
            if (++cnt[c - 'a'] == 2) {
                return c;
            }
        }
    }
}