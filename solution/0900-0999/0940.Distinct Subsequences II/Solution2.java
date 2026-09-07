class Solution {
    public int distinctSubseqII(String s) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[26];
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            int add = (ans + 1 + mod - f[j]) % mod;
            ans = (ans + add) % mod;
            f[j] = (f[j] + add) % mod;
        }
        return ans;
    }
}
