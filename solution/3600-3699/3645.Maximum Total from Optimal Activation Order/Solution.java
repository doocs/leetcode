class Solution {
    public long maxTotal(int[] value, int[] limit) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < value.length; ++i) {
            g.computeIfAbsent(limit[i], k -> new ArrayList<>()).add(value[i]);
        }
        long ans = 0;
        for (var e : g.entrySet()) {
            int lim = e.getKey();
            var vs = e.getValue();
            vs.sort((a, b) -> b - a);
            for (int i = 0; i < Math.min(lim, vs.size()); ++i) {
                ans += vs.get(i);
            }
        }
        return ans;
    }
}
