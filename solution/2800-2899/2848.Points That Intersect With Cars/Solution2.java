class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (var e : nums) {
            int start = e.get(0), end = e.get(1);
            d.merge(start, 1, Integer::sum);
            d.merge(end + 1, -1, Integer::sum);
        }
        int ans = 0, s = 0, last = 0;
        for (var e : d.entrySet()) {
            int cur = e.getKey(), v = e.getValue();
            if (s > 0) {
                ans += cur - last;
            }
            s += v;
            last = cur;
        }
        return ans;
    }
}
