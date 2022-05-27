class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        int i = 100000, j = -100000, ans = 100000;
        for (int k = 0; k < words.length; ++k) {
            String word = words[k];
            if (word.equals(word1)) {
                i = k;
            } else if (word.equals(word2)) {
                j = k;
            }
            ans = Math.min(ans, Math.abs(i - j));
        }
        return ans;
    }
}