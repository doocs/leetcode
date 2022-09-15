class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        int n = inventory.length;
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            int t = inventory[i];
            inventory[i] = inventory[j];
            inventory[j] = t;
        }
        long ans = 0;
        int i = 0;
        while (orders > 0) {
            while (i < n && inventory[i] >= inventory[0]) {
                ++i;
            }
            int nxt = i < n ? inventory[i] : 0;
            int cnt = i;
            long x = inventory[0] - nxt;
            long tot = cnt * x;
            if (tot > orders) {
                int decr = orders / cnt;
                long a1 = inventory[0] - decr + 1, an = inventory[0];
                ans += (a1 + an) * decr / 2 * cnt;
                ans += (a1 - 1) * (orders % cnt);
            } else {
                long a1 = nxt + 1, an = inventory[0];
                ans += (a1 + an) * x / 2 * cnt;
                inventory[0] = nxt;
            }
            orders -= tot;
            ans %= MOD;
        }
        return (int) ans;
    }
}