class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(
            points, (p1, p2) -> Math.hypot(p1[0], p1[1]) - Math.hypot(p2[0], p2[1]) > 0 ? 1 : -1);
        return Arrays.copyOfRange(points, 0, k);
    }
}
