class Solution {
    public int removeAlmostEqualCharacters(String word) {
        int ans = 0, n = word.length();
        for (int i = 1; i < n; ++i) {
            if (Math.abs(word.charAt(i) - word.charAt(i - 1)) < 2) {
                ++ans;
                ++i;
            }
        }
        return ans;
    }
}