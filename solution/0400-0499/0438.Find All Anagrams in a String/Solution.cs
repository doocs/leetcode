public class Solution {
    public IList<int> FindAnagrams(string s, string p) {
        int m = s.Length, n = p.Length;
        IList<int> ans = new List<int>();
        if (m < n) {
            return ans;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[p[i] - 'a'];
        }
        for (int i = 0, j = 0; i < m; ++i) {
            int k = s[i] - 'a';
            ++cnt2[k];
            while (cnt2[k] > cnt1[k]) {
                --cnt2[s[j++] - 'a'];
            }
            if (i - j + 1 == n) {
                ans.Add(j);
            }
        }
        return ans;
    }
}
