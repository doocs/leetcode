class Solution {
    private List<int[]> ans = new ArrayList<>();
    private boolean[] vis;
    private int[] t;
    private int n;

    public int[][] permute(int n) {
        this.n = n;
        t = new int[n];
        vis = new boolean[n + 1];
        dfs(0);
        return ans.toArray(new int[0][]);
    }

    private void dfs(int i) {
        if (i >= n) {
            ans.add(t.clone());
            return;
        }
        for (int j = 1; j <= n; ++j) {
            if (!vis[j] && (i == 0 || t[i - 1] % 2 != j % 2)) {
                vis[j] = true;
                t[i] = j;
                dfs(i + 1);
                vis[j] = false;
            }
        }
    }
}
