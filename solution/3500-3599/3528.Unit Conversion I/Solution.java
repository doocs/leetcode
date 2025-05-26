class Solution {
    private final int mod = (int) 1e9 + 7;
    private List<int[]>[] g;
    private int[] ans;
    private int n;

    public int[] baseUnitConversions(int[][] conversions) {
        n = conversions.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        ans = new int[n];
        for (var e : conversions) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }
        dfs(0, 1);
        return ans;
    }

    private void dfs(int s, long mul) {
        ans[s] = (int) mul;
        for (var e : g[s]) {
            dfs(e[0], mul * e[1] % mod);
        }
    }
}