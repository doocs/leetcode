class Solution {
    public int longestCommonPrefix(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        boolean rem = false;
        while (i < n && j < m) {
            if (s.charAt(i) != t.charAt(j)) {
                if (rem) {
                    break;
                }
                rem = true;
            } else {
                ++j;
            }
            ++i;
        }
        return j;
    }
}
