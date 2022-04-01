class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        int idx1 = 100000;
        int idx2 = -100000;
        int ans = 100000;
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            if (word.equals(word1)) {
                idx1 = i;
            } else if (word.equals(word2)) {
                idx2 = i;
            }
            ans = Math.min(ans, Math.abs(idx1 - idx2));
        }
        return ans;
    }
}