public class Solution {
    public int MinRectanglesToCoverPoints(int[][] points, int w) {
        Array.Sort(points, (a, b) => a[0] - b[0]);
        int ans = 0, x1 = -1;
        foreach (int[] p in points) {
            int x = p[0];
            if (x > x1) {
                ans++;
                x1 = x + w;
            }
        }
        return ans;
    }
}
