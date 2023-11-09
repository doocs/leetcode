public class Solution {
    public int CountHomogenous(string s) {
        long MOD = 1000000007;
        long ans = 0;
        for (int i = 0, j = 0; i < s.Length; i = j) {
            j = i;
            while (j < s.Length && s[j] == s[i]) {
                ++j;
            }
            int cnt = j - i;
            ans += (long) (1 + cnt) * cnt / 2;
            ans %= MOD;
        }
        return (int) ans;
    }
}
