class Solution {
    private char[] s;
    private boolean[] vis = new boolean['z' + 1];
    private List<String> ans = new ArrayList<>();
    private StringBuilder t = new StringBuilder();

    public String[] permutation(String S) {
        s = S.toCharArray();
        dfs(0);
        return ans.toArray(new String[0]);
    }

    private void dfs(int i) {
        if (i == s.length) {
            ans.add(t.toString());
            return;
        }
        for (char c : s) {
            if (vis[c]) {
                continue;
            }
            vis[c] = true;
            t.append(c);
            dfs(i + 1);
            t.deleteCharAt(t.length() - 1);
            vis[c] = false;
        }
    }
}