class Solution {
    private List<Integer>[] g;
    private String labels;
    private int[] ans;
    private int[] cnt;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        this.labels = labels;
        ans = new int[n];
        cnt = new int[26];
        dfs(0, -1);
        return ans;
    }

    private void dfs(int i, int fa) {
        int k = labels.charAt(i) - 'a';
        ans[i] -= cnt[k];
        cnt[k]++;
        for (int j : g[i]) {
            if (j != fa) {
                dfs(j, i);
            }
        }
        ans[i] += cnt[k];
    }
}