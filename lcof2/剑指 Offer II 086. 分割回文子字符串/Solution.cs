public class Solution {
    private int n;
    private string s;
    private bool[,] f;
    private IList<IList<string>> ans = new List<IList<string>>();
    private IList<string> t = new List<string>();

    public IList<IList<string>> Partition(string s) {
        n = s.Length;
        this.s = s;
        f = new bool[n, n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                f[i, j] = true;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i, j] = s[i] == s[j] && f[i + 1, j - 1];
            }
        }
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            ans.Add(new List<string>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i, j]) {
                t.Add(s.Substring(i, j + 1 - i));
                dfs(j + 1);
                t.RemoveAt(t.Count - 1);
            }
        }
    }
}