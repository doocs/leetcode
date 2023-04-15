public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int k;

    public IList<IList<int>> CombinationSum3(int k, int n) {
        this.k = k;
        dfs(1, n);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            if (t.Count == k) {
                ans.Add(new List<int>(t));
            }
            return;
        }
        if (i > 9 || i > s || t.Count >= k) {
            return;
        }
        t.Add(i);
        dfs(i + 1, s - i);
        t.RemoveAt(t.Count - 1);
        dfs(i + 1, s);
    }
}