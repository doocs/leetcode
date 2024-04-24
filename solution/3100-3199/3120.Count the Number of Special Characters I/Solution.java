class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] s = new boolean['z' + 1];
        for (int i = 0; i < word.length(); ++i) {
            s[word.charAt(i)] = true;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (s['a' + i] && s['A' + i]) {
                ++ans;
            }
        }
        return ans;
    }
}