class Solution {
    private List<Integer>[] g;
    private List<Integer>[] d;
    private char[] s;
    private int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = s.length();
        g = new List[n];
        d = new List[26];
        this.s = s.toCharArray();
        Arrays.setAll(g, k -> new ArrayList<>());
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parent[i]].add(i);
        }
        ans = new int[n];
        dfs(0, -1);
        return ans;
    }

    private void dfs(int i, int fa) {
        ans[i] = 1;
        int idx = s[i] - 'a';
        d[idx].add(i);
        for (int j : g[i]) {
            dfs(j, i);
        }
        int k = d[idx].size() > 1 ? d[idx].get(d[idx].size() - 2) : fa;
        if (k >= 0) {
            ans[k] += ans[i];
        }
        d[idx].remove(d[idx].size() - 1);
    }
}
