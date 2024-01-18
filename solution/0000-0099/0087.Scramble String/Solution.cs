public class Solution {
    private string s1;
    private string s2;
    private int[,,] f;

    public bool IsScramble(string s1, string s2) {
        int n = s1.Length;
        this.s1 = s1;
        this.s2 = s2;
        f = new int[n, n, n + 1];
        return dfs(0, 0, n);
    }

    private bool dfs(int i, int j, int k) {
        if (f[i, j, k] != 0) {
            return f[i, j, k] == 1;
        }
        if (k == 1) {
            return s1[i] == s2[j];
        }
        for (int h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                f[i, j, k] = 1;
                return true;
            }
            if (dfs(i, j + k - h, h) && dfs(i + h, j, k - h)) {
                f[i, j, k] = 1;
                return true;
            }
        }
        f[i, j, k] = -1;
        return false;
    }
}
