public class Solution {
    private int m;
    private int n;
    private string s1;
    private string s2;
    private string s3;
    private int[,] f;

    public bool IsInterleave(string s1, string s2, string s3) {
        m = s1.Length;
        n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        f = new int[m + 1, n + 1];
        return dfs(0, 0);
    }

    private bool dfs(int i, int j) {
        if (i >= m && j >= n) {
            return true;
        }
        if (f[i, j] != 0) {
            return f[i, j] == 1;
        }
        f[i, j] = -1;
        if (i < m && s1[i] == s3[i + j] && dfs(i + 1, j)) {
            f[i, j] = 1;
        }
        if (f[i, j] == -1 && j < n && s2[j] == s3[i + j] && dfs(i, j + 1)) {
            f[i, j] = 1;
        }
        return f[i, j] == 1;
    }
}
