class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(get('T', k, answerKey), get('F', k, answerKey));
    }

    public int get(char c, int k, String answerKey) {
        int l = 0, r = 0;
        while (r < answerKey.length()) {
            if (answerKey.charAt(r++) == c) {
                --k;
            }
            if (k < 0 && answerKey.charAt(l++) == c) {
                ++k;
            }
        }
        return r - l;
    }
}