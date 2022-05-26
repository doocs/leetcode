class Solution {
    public int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int[] e : lights) {
            int l = e[0] - e[1], r = e[0] + e[1];
            d.put(l, d.getOrDefault(l, 0) + 1);
            d.put(r + 1, d.getOrDefault(r + 1, 0) - 1);
        }
        int s = 0, mx = 0, ans = 0;
        for (Map.Entry<Integer, Integer> e : d.entrySet()) {
            s += e.getValue();
            if (s > mx) {
                mx = s;
                ans = e.getKey();
            }
        }
        return ans;
    }
}