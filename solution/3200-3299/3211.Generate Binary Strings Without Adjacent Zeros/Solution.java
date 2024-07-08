class Solution {
    private List<String> ans = new ArrayList<>();
    private StringBuilder t = new StringBuilder();
    private int n;

    public List<String> validStrings(int n) {
        this.n = n;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= n) {
            ans.add(t.toString());
            return;
        }
        for (int j = 0; j < 2; ++j) {
            if ((j == 0 && (i == 0 || t.charAt(i - 1) == '1')) || j == 1) {
                t.append(j);
                dfs(i + 1);
                t.deleteCharAt(t.length() - 1);
            }
        }
    }
}