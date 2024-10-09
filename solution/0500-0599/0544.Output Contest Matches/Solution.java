class Solution {
    public String findContestMatch(int n) {
        String[] s = new String[n];
        for (int i = 0; i < n; ++i) {
            s[i] = String.valueOf(i + 1);
        }
        for (; n > 1; n >>= 1) {
            for (int i = 0; i < n >> 1; ++i) {
                s[i] = String.format("(%s,%s)", s[i], s[n - i - 1]);
            }
        }
        return s[0];
    }
}