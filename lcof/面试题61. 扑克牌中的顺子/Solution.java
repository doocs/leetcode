class Solution {
    public boolean isStraight(int[] nums) {
        boolean[] vis = new boolean[14];
        int mi = 20, mx = -1;
        for (int x : nums) {
            if (x == 0) {
                continue;
            }
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
            mi = Math.min(mi, x);
            mx = Math.max(mx, x);
        }
        return mx - mi <= 4;
    }
}