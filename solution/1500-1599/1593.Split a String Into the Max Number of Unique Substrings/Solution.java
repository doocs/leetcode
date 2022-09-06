class Solution {
    private Set<String> vis = new HashSet<>();
    private int ans = 1;
    private String s;

    public int maxUniqueSplit(String s) {
        this.s = s;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int t) {
        if (i >= s.length()) {
            ans = Math.max(ans, t);
            return;
        }
        for (int j = i + 1; j <= s.length(); ++j) {
            String x = s.substring(i, j);
            if (vis.add(x)) {
                dfs(j, t + 1);
                vis.remove(x);
            }
        }
    }
}