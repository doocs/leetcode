class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numOfSubarrays(int[] arr) {
        int[] counter = new int[2];
        int s = 0, ans = 0;
        for (int v : arr) {
            s += v;
            ++counter[s % 2];
            if (s % 2 == 1) {
                ans = (ans + 1 + counter[0]) % MOD;
            } else {
                ans = (ans + counter[1]) % MOD;
            }
        }
        return ans;
    }
}