class Solution {
    private final List<Integer> ans = new ArrayList<>();
    private List<int[]>[] g;
    private char[] start;
    private char[] target;

    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n - 1; ++i) {
            int a = edges[i][0], b = edges[i][1];
            g[a].add(new int[] {b, i});
            g[b].add(new int[] {a, i});
        }
        this.start = start.toCharArray();
        this.target = target.toCharArray();
        if (dfs(0, -1)) {
            return List.of(-1);
        }
        Collections.sort(ans);
        return ans;
    }

    private boolean dfs(int a, int fa) {
        boolean rev = start[a] != target[a];
        for (var e : g[a]) {
            int b = e[0], i = e[1];
            if (b != fa && dfs(b, a)) {
                ans.add(i);
                rev = !rev;
            }
        }
        return rev;
    }
}
