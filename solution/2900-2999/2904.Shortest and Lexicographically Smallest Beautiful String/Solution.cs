public class Solution {
    public string ShortestBeautifulSubstring(string s, int k) {
        int n = s.Length;
        string ans = "";

        for (int i = 0; i < n; i++) {
            for (int j = i + k; j <= n; j++) {
                string t = s.Substring(i, j - i);

                int cnt = 0;
                foreach (char c in t.ToCharArray()) {
                    cnt += c - '0';
                }

                if (cnt == k &&
                    (ans == "" ||
                     j - i < ans.Length ||
                     (j - i == ans.Length && string.Compare(t, ans, StringComparison.Ordinal) < 0))) {
                    ans = t;
                }
            }
        }

        return ans;
    }
}