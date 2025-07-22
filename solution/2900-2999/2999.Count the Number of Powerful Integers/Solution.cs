public class Solution {
    private string s;
    private string t;
    private long?[] f;
    private int limit;

    public long NumberOfPowerfulInt(long start, long finish, int limit, string s) {
        this.s = s;
        this.limit = limit;
        t = (start - 1).ToString();
        f = new long?[20];
        long a = Dfs(0, true);
        t = finish.ToString();
        f = new long?[20];
        long b = Dfs(0, true);
        return b - a;
    }

    private long Dfs(int pos, bool lim) {
        if (t.Length < s.Length) {
            return 0;
        }
        if (!lim && f[pos].HasValue) {
            return f[pos].Value;
        }
        if (t.Length - pos == s.Length) {
            return lim ? (string.Compare(s, t.Substring(pos)) <= 0 ? 1 : 0) : 1;
        }
        int up = lim ? t[pos] - '0' : 9;
        up = Math.Min(up, limit);
        long ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += Dfs(pos + 1, lim && i == (t[pos] - '0'));
        }
        if (!lim) {
            f[pos] = ans;
        }
        return ans;
    }
}