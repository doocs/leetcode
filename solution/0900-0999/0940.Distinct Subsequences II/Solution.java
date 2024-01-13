class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int distinctSubseqII(String s) {
        int[] dp = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            dp[j] = sum(dp) + 1;
        }
        return sum(dp);
    }

    private int sum(int[] arr) {
        int x = 0;
        for (int v : arr) {
            x = (x + v) % MOD;
        }
        return x;
    }
}