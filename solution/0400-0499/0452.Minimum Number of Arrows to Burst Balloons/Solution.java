class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> a[1] < b[1] ? -1 : 1);
        int ans = 1;
        int x = points[0][1];
        for (int[] v : points) {
            int a = v[0], b = v[1];
            if (a > x) {
                ++ans;
                x = b;
            }
        }
        return ans;
    }
}