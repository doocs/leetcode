public class Solution {
    private List<string> ans = new List<string>();
    private int n;

    public List<string> GenerateParenthesis(int n) {
        this.n = n;
        Dfs(0, 0, "");
        return ans;
    }

    private void Dfs(int l, int r, string t) {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.Add(t);
            return;
        }
        Dfs(l + 1, r, t + "(");
        Dfs(l, r + 1, t + ")");
    }
}