class Solution {
    public char repeatedCharacter(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            if (++cnt[c - 'a'] == 2) {
                return c;
            }
        }
        return '.';
    }
}