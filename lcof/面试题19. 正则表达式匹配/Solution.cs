public class Solution {
    private string s;
    private string p;
    private int m;
    private int n;
    private int[,] f;

    public bool IsMatch(string s, string p) {
        m = s.Length;
        n = p.Length;
        f = new int[m + 1, n + 1];
        this.s = s;
        this.p = p;
        return dfs(0, 0);
    }

    private bool dfs(int i, int j) {
        if (j >= n) {
            return i == m;
        }
        if (f[i, j] != 0) {
            return f[i, j] == 1;
        }
        int res = -1;
        if (j + 1 < n && p[j + 1] == '*') {
            if (dfs(i, j + 2) || (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i + 1, j))) {
                res = 1;
            }
        } else if (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i + 1, j + 1)) {
            res = 1;
        }
        f[i, j] = res;
        return res == 1;
    }
}
