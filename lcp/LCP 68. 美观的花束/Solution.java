class Solution {
    public int beautifulBouquet(int[] flowers, int cnt) {
        int mx = 0;
        for (int x : flowers) {
            mx = Math.max(mx, x);
        }
        int[] d = new int[mx + 1];
        long ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int i = 0, j = 0; i < flowers.length; ++i) {
            ++d[flowers[i]];
            while (d[flowers[i]] > cnt) {
                --d[flowers[j++]];
            }
            ans = (ans + i - j + 1) % mod;
        }
        return (int) ans;
    }
}