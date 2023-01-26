class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int tx = target[0], ty = target[1];
        for (var g : ghosts) {
            int x = g[0], y = g[1];
            if (Math.abs(tx - x) + Math.abs(ty - y) <= Math.abs(tx) + Math.abs(ty)) {
                return false;
            }
        }
        return true;
    }
}