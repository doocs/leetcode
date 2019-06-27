class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int res = 1;
        int pre = points[0][1];
        for (int i = 1; i < points.length; ++i) {
            if (points[i][0] > pre) {
                ++res;
                pre = points[i][1];
            }
        }
        return res;
    }
}
