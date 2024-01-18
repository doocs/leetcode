class Solution {
    public int maxScore(String s) {
        int t = 0;
        if (s.charAt(0) == '0') {
            t++;
        }
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                t++;
            }
        }
        int ans = t;
        for (int i = 1; i < s.length() - 1; ++i) {
            t += s.charAt(i) == '0' ? 1 : -1;
            ans = Math.max(ans, t);
        }
        return ans;
    }
}