class Solution {
    public int maxScore(String s) {
        int l = 0, r = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++r;
            }
        }
        int ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            l += (s.charAt(i) - '0') ^ 1;
            r -= s.charAt(i) - '0';
            ans = Math.max(ans, l + r);
        }
        return ans;
    }
}
