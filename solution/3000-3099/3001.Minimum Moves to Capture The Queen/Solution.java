class Solution {
    private final int[] dirs1 = {-1, 0, 1, 0, -1};
    private final int[] dirs2 = {-1, 1, 1, -1, -1};
    private int e, f;

    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        this.e = e;
        this.f = f;
        return check(dirs1, a, b, c, d) || check(dirs2, c, d, a, b) ? 1 : 2;
    }

    private boolean check(int[] dirs, int sx, int sy, int bx, int by) {
        for (int d = 0; d < 4; ++d) {
            for (int k = 1; k < 8; ++k) {
                int x = sx + dirs[d] * k;
                int y = sy + dirs[d + 1] * k;
                if (x < 1 || x > 8 || y < 1 || y > 8 || (x == bx && y == by)) {
                    break;
                }
                if (x == e && y == f) {
                    return true;
                }
            }
        }
        return false;
    }
}