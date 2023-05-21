class Solution {
    public String makeSmallestPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0, j = cs.length - 1; i < j; ++i, --j) {
            if (cs[i] != cs[j]) {
                cs[i] = cs[j] = cs[i] < cs[j] ? cs[i] : cs[j];
            }
        }
        return String.valueOf(cs);
    }
}