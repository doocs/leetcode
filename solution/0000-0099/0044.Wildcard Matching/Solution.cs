public class Solution {
    private bool?[,] f;
    private char[] s;
    private char[] p;
    private int m;
    private int n;

    public bool IsMatch(string s, string p) {
        this.s = s.ToCharArray();
        this.p = p.ToCharArray();
        m = s.Length;
        n = p.Length;
        f = new bool?[m, n];
        return Dfs(0, 0);
    }

    private bool Dfs(int i, int j) {
        if (i >= m) {
            return j >= n || (p[j] == '*' && Dfs(i, j + 1));
        }
        if (j >= n) {
            return false;
        }
        if (f[i, j] != null) {
            return f[i, j].Value;
        }
        if (p[j] == '*') {
            f[i, j] = Dfs(i + 1, j) || Dfs(i + 1, j + 1) || Dfs(i, j + 1);
        } else {
            f[i, j] = (p[j] == '?' || s[i] == p[j]) && Dfs(i + 1, j + 1);
        }
        return f[i, j].Value;
    }
}
