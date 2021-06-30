class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, "", 0, 0, n);
        return res;
    }

    private void dfs(List<String> res, String ans, int l, int r, int n) {
        if (ans.length() == (n << 1)) {
            res.add(ans);
            return;
        }
        if (l < n) {
            dfs(res, ans + "(", l + 1, r, n);
        }
        if (r < l) {
            dfs(res, ans + ")", l, r + 1, n);
        }
    }
}