class Solution {
    public int maxPower(String s) {
        int ans = 0, t = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i == 0 || s.charAt(i) == s.charAt(i - 1)) {
                ++t;
            } else {
                t = 1;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}