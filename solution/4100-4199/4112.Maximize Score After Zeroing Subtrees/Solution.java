class Solution {
    private List<Integer>[] g;
    private int[] op;
    private int[] score;

    public int maxScore(int[] parent, int[] op, int[] score) {
        this.op = op;
        this.score = score;
        int n = parent.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (g[i].isEmpty()) {
            return score[i];
        }
        int tot = score[i];
        int mn = Integer.MAX_VALUE;
        for (int j : g[i]) {
            int t = dfs(j);
            mn = Math.min(mn, t);
            tot += t;
        }
        if (op[i] == 0) {
            return tot - Math.min(0, mn);
        }
        return tot - mn;
    }
}