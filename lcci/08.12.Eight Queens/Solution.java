class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        String[][] g = new String[n][n];
        for (int i = 0; i < n; ++i) {
            String[] t = new String[n];
            Arrays.fill(t, ".");
            g[i] = t;
        }
        boolean[] col = new boolean[n];
        boolean[] dg = new boolean[2 * n];
        boolean[] udg = new boolean[2 * n];
        dfs(0, n, col, dg, udg, g, res);
        return res;
    }

    private void dfs(int u, int n, boolean[] col, boolean[] dg, boolean[] udg, String[][] g, List<List<String>> res) {
        if (u == n) {
            List<String> t = new ArrayList<>();
            for (String[] e : g) {
                t.add(String.join("", e));
            }
            res.add(t);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!col[i] && !dg[u + i] && !udg[n - u + i]) {
                g[u][i] = "Q";
                col[i] = dg[u + i] = udg[n - u + i] = true;
                dfs(u + 1, n, col, dg, udg, g, res);
                g[u][i] = ".";
                col[i] = dg[u + i] = udg[n - u + i] = false;
            }
        }
    }
}