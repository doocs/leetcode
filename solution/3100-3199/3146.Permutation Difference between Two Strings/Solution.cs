public class Solution {
    public int FindPermutationDifference(string s, string t) {
        int[] d = new int[26];
        int n = s.Length;
        for (int i = 0; i < n; ++i) {
            d[s[i] - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Abs(d[t[i] - 'a'] - i);
        }
        return ans;
    }
}
