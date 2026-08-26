public class Solution {
    public string ShortestBeautifulSubstring(string s, int k) {
        int i = 0, j = 0, cnt = 0;
        int n = s.Length;
        string ans = "";

        while (j < n) {
            cnt += s[j] - '0';

            while (cnt > k || (i < j && s[i] == '0')) {
                cnt -= s[i] - '0';
                i++;
            }

            j++;

            string t = s.Substring(i, j - i);

            if (cnt == k && 
                (ans == "" || 
                 j - i < ans.Length || 
                 (j - i == ans.Length && string.Compare(t, ans, StringComparison.Ordinal) < 0))) {
                ans = t;
            }
        }

        return ans;
    }
}