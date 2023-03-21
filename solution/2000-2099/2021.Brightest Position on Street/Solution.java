class Solution {
    public int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (var x : lights) {
            int l = x[0] - x[1], r = x[0] + x[1];
            d.merge(l, 1, Integer::sum);
            d.merge(r + 1, -1, Integer::sum);
        }
        int ans = 0, s = 0, mx = 0;
        for (var x : d.entrySet()) {
            int v = x.getValue();
            s += v;
            if (mx < s) {
                mx = s;
                ans = x.getKey();
            }
        }
        return ans;
    }
}