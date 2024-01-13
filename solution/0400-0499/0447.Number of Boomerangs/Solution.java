class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p1 : points) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int[] p2 : points) {
                int d = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
                ans += cnt.getOrDefault(d, 0);
                cnt.merge(d, 1, Integer::sum);
            }
        }
        return ans << 1;
    }
}