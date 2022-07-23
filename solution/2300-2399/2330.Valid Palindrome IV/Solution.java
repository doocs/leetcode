class Solution {
    public boolean makePalindrome(String s) {
        int t = 0;
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                ++t;
            }
        }
        return t <= 2;
    }
}