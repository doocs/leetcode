public class Solution {
    public int ScoreOfString(string s) {
        int ans = 0;
        for (int i = 1; i < s.Length; ++i) {
            ans += Math.Abs(s[i] - s[i - 1]);
        }
        return ans;
    }
}
