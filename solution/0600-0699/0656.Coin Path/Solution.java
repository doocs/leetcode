class Solution {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        List<Integer> ans = new ArrayList<>();
        if (coins[n - 1] == -1) {
            return ans;
        }
        int[] f = new int[n];
        final int inf = 1 << 30;
        Arrays.fill(f, inf);
        f[n - 1] = coins[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (coins[i] != -1) {
                for (int j = i + 1; j < Math.min(n, i + maxJump + 1); ++j) {
                    if (f[i] > f[j] + coins[i]) {
                        f[i] = f[j] + coins[i];
                    }
                }
            }
        }
        if (f[0] == inf) {
            return ans;
        }
        for (int i = 0, s = f[0]; i < n; ++i) {
            if (f[i] == s) {
                s -= coins[i];
                ans.add(i + 1);
            }
        }
        return ans;
    }
}