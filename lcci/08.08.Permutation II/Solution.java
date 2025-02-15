class Solution {
    private char[] s;
    private char[] t;
    private boolean[] vis;
    private List<String> ans = new ArrayList<>();

    public String[] permutation(String S) {
        int n = S.length();
        s = S.toCharArray();
        Arrays.sort(s);
        t = new char[n];
        vis = new boolean[n];
        dfs(0);
        return ans.toArray(new String[0]);
    }

    private void dfs(int i) {
        if (i >= s.length) {
            ans.add(new String(t));
            return;
        }
        for (int j = 0; j < s.length; ++j) {
            if (!vis[j] && (j == 0 || s[j] != s[j - 1] || vis[j - 1])) {
                vis[j] = true;
                t[i] = s[j];
                dfs(i + 1);
                vis[j] = false;
            }
        }
    }
}
