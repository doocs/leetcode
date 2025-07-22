public class Solution {
    private int m;
    private char[] s;
    private int?[,] f;

    public int CountDigitOne(int n) {
        s = n.ToString().ToCharArray();
        m = s.Length;
        f = new int?[m, m];
        return Dfs(0, 0, true);
    }

    private int Dfs(int i, int cnt, bool limit) {
        if (i >= m) {
            return cnt;
        }
        if (!limit && f[i, cnt] != null) {
            return f[i, cnt].Value;
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            ans += Dfs(i + 1, cnt + (j == 1 ? 1 : 0), limit && j == up);
        }
        if (!limit) {
            f[i, cnt] = ans;
        }
        return ans;
    }
}
