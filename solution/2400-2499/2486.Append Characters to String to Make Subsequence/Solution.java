class Solution {
    public int appendCharacters(String s, String t) {
        int m = s.length(), n = t.length();
        for (int i = 0, j = 0; j < n; ++j) {
            while (i < m && s.charAt(i) != t.charAt(j)) {
                ++i;
            }
            if (i++ == m) {
                return n - j;
            }
        }
        return 0;
    }
}