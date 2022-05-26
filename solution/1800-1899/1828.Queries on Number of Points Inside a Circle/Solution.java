class Solution {
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] ans = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            int x0 = query[0], y0 = query[1], r = query[2];
            for (int[] point : points) {
                int x = point[0], y = point[1];
                int dx = x - x0, dy = y - y0;
                if (dx * dx + dy * dy <= r * r) {
                    ++ans[i];
                }
            }
            ++i;
        }
        return ans;
    }
}