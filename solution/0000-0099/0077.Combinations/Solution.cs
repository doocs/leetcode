public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int n;
    private int k;

    public IList<IList<int>> Combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.Count == k) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i > n) {
            return;
        }
        t.Add(i);
        dfs(i + 1);
        t.RemoveAt(t.Count - 1);
        dfs(i + 1);
    }
}
