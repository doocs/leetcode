class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int m = word.length(), n = abbr.length();
        int i = 0, j = 0, x = 0;
        for (; i < m && j < n; ++j) {
            char c = abbr.charAt(j);
            if (Character.isDigit(c)) {
                if (c == '0' && x == 0) {
                    return false;
                }
                x = x * 10 + (c - '0');
            } else {
                i += x;
                x = 0;
                if (i >= m || word.charAt(i) != c) {
                    return false;
                }
                ++i;
            }
        }
        return i + x == m && j == n;
    }
}