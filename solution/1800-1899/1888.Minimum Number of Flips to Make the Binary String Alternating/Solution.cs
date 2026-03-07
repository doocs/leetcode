public class Solution {
    public int MinFlips(string s) {
        int n = s.Length;
        string target = "01";
        int cnt = 0;

        for (int i = 0; i < n; ++i) {
            if (s[i] != target[i & 1]) {
                ++cnt;
            }
        }

        int ans = Math.Min(cnt, n - cnt);

        for (int i = 0; i < n; ++i) {
            if (s[i] != target[i & 1]) {
                --cnt;
            }
            if (s[i] != target[(i + n) & 1]) {
                ++cnt;
            }
            ans = Math.Min(ans, Math.Min(cnt, n - cnt));
        }

        return ans;
    }
}
