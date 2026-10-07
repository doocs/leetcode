class Solution {
    public int[] baseUnitConversions(int[][] conversions) {
        final int mod = (int) 1e9 + 7;
        int n = conversions.length + 1;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : conversions) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }
        int[] ans = new int[n];
        Deque<long[]> stk = new ArrayDeque<>();
        stk.push(new long[] {0, 1});
        while (!stk.isEmpty()) {
            long[] cur = stk.pop();
            int s = (int) cur[0];
            long mul = cur[1];
            ans[s] = (int) mul;
            for (var e : g[s]) {
                stk.push(new long[] {e[0], mul * e[1] % mod});
            }
        }
        return ans;
    }
}
