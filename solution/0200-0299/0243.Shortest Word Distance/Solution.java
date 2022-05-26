class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int w1 = -1, w2 = -1, ans = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (Objects.equals(words[i], word1)) {
                w1 = i;
                if (w2 >= 0) {
                    ans = Math.min(w1 - w2, ans);
                }
            } else if (Objects.equals(words[i], word2)) {
                w2 = i;
                if (w1 >= 0) {
                    ans = Math.min(w2 - w1, ans);
                }
            }
        }
        return ans;
    }
}