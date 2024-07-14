class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        final int inf = 1 << 29;
        int i = inf, j = -inf, ans = inf;
        for (int k = 0; k < words.length; ++k) {
            if (words[k].equals(word1)) {
                i = k;
            } else if (words[k].equals(word2)) {
                j = k;
            }
            ans = Math.min(ans, Math.abs(i - j));
        }
        return ans;
    }
}