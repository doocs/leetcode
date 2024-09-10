class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] d = new int[102];
        for (var e : nums) {
            int start = e.get(0), end = e.get(1);
            ++d[start];
            --d[end + 1];
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            if (s > 0) {
                ++ans;
            }
        }
        return ans;
    }
}
