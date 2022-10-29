class Solution {
    public static final int MOD = (int) 1e9 + 7;

    public int countDistinctStrings(String s, int k) {
        int ans = 1;
        for (int i = 0; i < s.length() - k + 1; ++i) {
            ans = (ans * 2) % MOD;
        }
        return ans;
    }
}