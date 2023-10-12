class Solution {
    private int n;
    private char[] cs;
    private List<String> ans = new ArrayList<>();
    private boolean[] vis;
    private StringBuilder t = new StringBuilder();

    public String[] permutation(String S) {
        cs = S.toCharArray();
        n = cs.length;
        Arrays.sort(cs);
        vis = new boolean[n];
        dfs(0);
        return ans.toArray(new String[0]);
    }

    private void dfs(int i) {
        if (i == n) {
            ans.add(t.toString());
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && !vis[j - 1] && cs[j] == cs[j - 1])) {
                continue;
            }
            vis[j] = true;
            t.append(cs[j]);
            dfs(i + 1);
            t.deleteCharAt(t.length() - 1);
            vis[j] = false;
        }
    }
}