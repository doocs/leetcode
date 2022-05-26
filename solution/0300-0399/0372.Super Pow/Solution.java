class Solution {
    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        int ans = 1;
        for (int i = b.length - 1; i >= 0; --i) {
            ans = (int) ((long) ans * quickPowAndMod(a, b[i]) % MOD);
            a = quickPowAndMod(a, 10);
        }
        return ans;
    }

    private int quickPowAndMod(int a, int b) {
        int ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * (a % MOD)) % MOD;
            }
            b >>= 1;
            a = (a % MOD) * (a % MOD) % MOD;
        }
        return ans;
    }
}