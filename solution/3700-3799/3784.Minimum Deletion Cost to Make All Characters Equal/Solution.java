class Solution {
    public long minCost(String s, int[] cost) {
        long tot = 0;
        Map<Character, Long> g = new HashMap<>(26);
        for (int i = 0; i < cost.length; ++i) {
            tot += cost[i];
            g.merge(s.charAt(i), (long) cost[i], Long::sum);
        }
        long ans = tot;
        for (long v : g.values()) {
            ans = Math.min(ans, tot - v);
        }
        return ans;
    }
}
