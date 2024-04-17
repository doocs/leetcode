class Solution {
    private char[] s;
    private int k;

    public int maxConsecutiveAnswers(String answerKey, int k) {
        s = answerKey.toCharArray();
        this.k = k;
        return Math.max(f('T'), f('F'));
    }

    private int f(char c) {
        int cnt = 0, ans = 0;
        for (int i = 0, j = 0; i < s.length; ++i) {
            cnt += s[i] == c ? 1 : 0;
            while (cnt > k) {
                cnt -= s[j] == c ? 1 : 0;
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}