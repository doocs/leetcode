public class Solution {
    public int FindMinArrowShots(int[][] points) {
        Array.Sort(points, (a, b) => a[1] < b[1] ? -1 : a[1] > b[1] ? 1 : 0);
        int ans = 0;
        long last = long.MinValue;
        foreach (var point in points) {
            if (point[0] > last) {
                ++ans;
                last = point[1];
            }
        }
        return ans;
    }
}