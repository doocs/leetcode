public class Solution {
    public string GetHappyString(int n, int k) {
        List<string> ans = new List<string>();
        StringBuilder s = new StringBuilder();

        void Dfs() {
            if (s.Length == n) {
                ans.Add(s.ToString());
                return;
            }
            if (ans.Count >= k) {
                return;
            }
            foreach (char c in "abc") {
                if (s.Length == 0 || s[s.Length - 1] != c) {
                    s.Append(c);
                    Dfs();
                    s.Length--;
                }
            }
        }

        Dfs();
        return ans.Count < k ? "" : ans[k - 1];
    }
}
