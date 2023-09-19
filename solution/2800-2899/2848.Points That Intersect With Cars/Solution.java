class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] d = new int[110];
        for (var e : nums) {
            d[e.get(0)]++;
            d[e.get(1) + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            if (s > 0) {
                ans++;
            }
        }
        return ans;
    }
}