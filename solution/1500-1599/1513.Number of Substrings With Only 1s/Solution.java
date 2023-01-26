class Solution {
    public int numSub(String s) {
        final int mod = (int) 1e9 + 7;
        int ans = 0, cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            cnt = s.charAt(i) == '1' ? cnt + 1 : 0;
            ans = (ans + cnt) % mod;
        }
        return ans;
    }
}