class Solution {
    private int[][] circles;
    private int xCorner, yCorner;
    private boolean[] vis;

    public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
        int n = circles.length;
        this.circles = circles;
        this.xCorner = xCorner;
        this.yCorner = yCorner;
        vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            var c = circles[i];
            int x = c[0], y = c[1], r = c[2];
            if (inCircle(0, 0, x, y, r) || inCircle(xCorner, yCorner, x, y, r)) {
                return false;
            }
            if (!vis[i] && crossLeftTop(x, y, r) && dfs(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean inCircle(long x, long y, long cx, long cy, long r) {
        return (x - cx) * (x - cx) + (y - cy) * (y - cy) <= r * r;
    }

    private boolean crossLeftTop(long cx, long cy, long r) {
        boolean a = Math.abs(cx) <= r && (cy >= 0 && cy <= yCorner);
        boolean b = Math.abs(cy - yCorner) <= r && (cx >= 0 && cx <= xCorner);
        return a || b;
    }

    private boolean crossRightBottom(long cx, long cy, long r) {
        boolean a = Math.abs(cx - xCorner) <= r && (cy >= 0 && cy <= yCorner);
        boolean b = Math.abs(cy) <= r && (cx >= 0 && cx <= xCorner);
        return a || b;
    }

    private boolean dfs(int i) {
        var c = circles[i];
        long x1 = c[0], y1 = c[1], r1 = c[2];
        if (crossRightBottom(x1, y1, r1)) {
            return true;
        }
        vis[i] = true;
        for (int j = 0; j < circles.length; ++j) {
            var c2 = circles[j];
            long x2 = c2[0], y2 = c2[1], r2 = c2[2];
            if (vis[j]) {
                continue;
            }
            if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > (r1 + r2) * (r1 + r2)) {
                continue;
            }
            if (x1 * r2 + x2 * r1 < (r1 + r2) * xCorner && y1 * r2 + y2 * r1 < (r1 + r2) * yCorner
                && dfs(j)) {
                return true;
            }
        }
        return false;
    }
}
