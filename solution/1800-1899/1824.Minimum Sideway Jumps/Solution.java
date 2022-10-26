class Solution {
    private int inf = 0x3f3f3f3f;
    
    public int minSideJumps(int[] obstacles) {
        int[] f = new int[] {1, 0, 1};
        for (int i = 1; i < obstacles.length; ++i) {
            int v = obstacles[i];
            int[] g = new int[] {inf, inf, inf};
            for (int j = 0; j < 3; ++j) {
                if (v != j + 1) {
                    g[j] = f[j];
                }
            }
            if (v != 1) {
                g[0] = Math.min(g[0], Math.min(g[1], g[2]) + 1);
            }
            if (v != 2) {
                g[1] = Math.min(g[1], Math.min(g[0], g[2]) + 1);
            }
            if (v != 3) {
                g[2] = Math.min(g[2], Math.min(g[0], g[1]) + 1);
            }
            f = g;
        }
        return Math.min(f[0], Math.min(f[1], f[2]));
    }
}