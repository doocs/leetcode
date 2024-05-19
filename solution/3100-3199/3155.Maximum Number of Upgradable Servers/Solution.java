class Solution {
    public int[] maxUpgrades(int[] count, int[] upgrade, int[] sell, int[] money) {
        int n = count.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Math.min(count[i], (int) ((1L * count[i] * sell[i] + money[i]) / (upgrade[i] + sell[i])));
        }
        return ans;
    }
}