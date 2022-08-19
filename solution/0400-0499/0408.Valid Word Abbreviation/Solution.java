class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int m = word.length(), n = abbr.length();
        int i = 0, j = 0;
        while (i < m) {
            if (j >= n) {
                return false;
            }
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;
                ++j;
                continue;
            }
            int k = j;
            while (k < n && Character.isDigit(abbr.charAt(k))) {
                ++k;
            }
            String t = abbr.substring(j, k);
            if (j == k || t.charAt(0) == '0' || Integer.parseInt(t) == 0) {
                return false;
            }
            i += Integer.parseInt(t);
            j = k;
        }
        return i == m && j == n;
    }
}