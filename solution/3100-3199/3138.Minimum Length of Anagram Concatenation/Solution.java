class Solution {
    private int n;
    private char[] s;
    private int[] cnt = new int[26];

    public int minAnagramLength(String s) {
        n = s.length();
        this.s = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            ++cnt[this.s[i] - 'a'];
        }
        for (int i = 1;; ++i) {
            if (n % i == 0 && check(i)) {
                return i;
            }
        }
    }

    private boolean check(int k) {
        for (int i = 0; i < n; i += k) {
            int[] cnt1 = new int[26];
            for (int j = i; j < i + k; ++j) {
                ++cnt1[s[j] - 'a'];
            }
            for (int j = 0; j < 26; ++j) {
                if (cnt1[j] * (n / k) != cnt[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}