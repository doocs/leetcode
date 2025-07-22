class Solution {
    private char[] s;

    public boolean validPalindrome(String S) {
        this.s = S.toCharArray();
        for (int i = 0, j = s.length - 1; i < j; ++i, --j) {
            if (s[i] != s[j]) {
                return check(i + 1, j) || check(i, j - 1);
            }
        }
        return true;
    }

    private boolean check(int i, int j) {
        for (; i < j; ++i, --j) {
            if (s[i] != s[j]) {
                return false;
            }
        }
        return true;
    }
}
