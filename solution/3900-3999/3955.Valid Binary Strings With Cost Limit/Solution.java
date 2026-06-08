class Solution {
    private int n;
    private int k;
    private List<String> ans;
    private StringBuilder path;

    public List<String> generateValidStrings(int n, int k) {
        this.n = n;
        this.k = k;
        ans = new ArrayList<>();
        path = new StringBuilder();

        dfs(0, 0);

        return ans;
    }

    private void dfs(int i, int tot) {
        if (i >= n) {
            ans.add(path.toString());
            return;
        }

        path.append('0');
        dfs(i + 1, tot);
        path.deleteCharAt(path.length() - 1);

        if ((path.isEmpty() || path.charAt(path.length() - 1) == '0') && tot + i <= k) {
            path.append('1');
            dfs(i + 1, tot + i);
            path.deleteCharAt(path.length() - 1);
        }
    }
}