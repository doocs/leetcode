class Solution {
    public int numSub(String s) {
        final int mod = 1_000_000_007;
        int ans = 0, cur = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                cur = 0;
            } else {
                cur++;
                ans = (ans + cur) % mod;
            }
        }
        return ans;
    }
}
