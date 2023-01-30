class Solution {
    public int countLatticePoints(int[][] circles) {
        int mx = 0, my = 0;
        for (var c : circles) {
            mx = Math.max(mx, c[0] + c[2]);
            my = Math.max(my, c[1] + c[2]);
        }
        int ans = 0;
        for (int i = 0; i <= mx; ++i) {
            for (int j = 0; j <= my; ++j) {
                for (var c : circles) {
                    int dx = i - c[0], dy = j - c[1];
                    if (dx * dx + dy * dy <= c[2] * c[2]) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}