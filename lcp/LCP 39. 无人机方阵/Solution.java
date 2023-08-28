class Solution {
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] row : source) {
            for (int x : row) {
                cnt.merge(x, 1, Integer::sum);
            }
        }
        for (int[] row : target) {
            for (int x : row) {
                cnt.merge(x, -1, Integer::sum);
            }
        }
        int ans = 0;
        for (int v : cnt.values()) {
            ans += Math.abs(v);
        }
        return ans / 2;
    }
}