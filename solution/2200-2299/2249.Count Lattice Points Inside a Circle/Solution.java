class Solution {
    public int countLatticePoints(int[][] circles) {
        int ans = 0;
        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= 200; j++) {
                for (int[] circle : circles) {
                    int x = circle[0], y = circle[1], r = circle[2];
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= r * r) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}