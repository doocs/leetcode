class Solution {
    private List<Integer>[] g;
    private int[] colors;
    private int[] size;
    private int ans;

    public int maximumSubtreeSize(int[][] edges, int[] colors) {
        int n = edges.length + 1;
        g = new List[n];
        size = new int[n];
        this.colors = colors;
        Arrays.fill(size, 1);
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        return ans;
    }

    private boolean dfs(int a, int fa) {
        boolean ok = true;
        for (int b : g[a]) {
            if (b != fa) {
                boolean t = dfs(b, a);
                ok = ok && colors[a] == colors[b] && t;
                size[a] += size[b];
            }
        }
        if (ok) {
            ans = Math.max(ans, size[a]);
        }
        return ok;
    }
}