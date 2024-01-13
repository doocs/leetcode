class Solution {
    public int findMinArrowShots(int[][] points) {
        // 直接 a[1] - b[1] 可能会溢出
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        long last = -(1L << 60);
        for (var p : points) {
            int a = p[0], b = p[1];
            if (a > last) {
                ++ans;
                last = b;
            }
        }
        return ans;
    }
}