class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int left0 = 0, right0 = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '0') {
                ++right0;
            }
        }
        int ans = Math.min(right0, n - right0);
        for (int i = 1; i <= n; ++i) {
            int x = s.charAt(i - 1) == '0' ? 0 : 1;
            right0 -= x ^ 1;
            left0 += x ^ 1;
            ans = Math.min(ans, i - left0 + right0);
        }
        return ans;
    }
}