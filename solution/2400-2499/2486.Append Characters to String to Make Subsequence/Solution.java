class Solution {
    public int appendCharacters(String s, String t) {
        int n = t.length(), j = 0;
        for (int i = 0; i < s.length() && j < n; ++i) {
            if (s.charAt(i) == t.charAt(j)) {
                ++j;
            }
        }
        return n - j;
    }
}