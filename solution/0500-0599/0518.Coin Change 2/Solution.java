class Solution {
    public int change(int amount, int[] coins) {
        int[] f = new int[amount + 1];
        f[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                f[i] += f[i - coin];
            }
        }
        return f[amount];
    }
}
