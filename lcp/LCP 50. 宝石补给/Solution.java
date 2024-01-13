class Solution {
    public int giveGem(int[] gem, int[][] operations) {
        for (var op : operations) {
            int x = op[0], y = op[1];
            int v = gem[x] >> 1;
            gem[y] += v;
            gem[x] -= v;
        }
        int mx = 0, mi = 1 << 30;
        for (int x : gem) {
            mx = Math.max(mx, x);
            mi = Math.min(mi, x);
        }
        return mx - mi;
    }
}