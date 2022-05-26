class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m || i < n; ++i) {
            if (i < m) {
                res.append(word1.charAt(i));
            }
            if (i < n) {
                res.append(word2.charAt(i));
            }
        }
        return res.toString();
    }
}