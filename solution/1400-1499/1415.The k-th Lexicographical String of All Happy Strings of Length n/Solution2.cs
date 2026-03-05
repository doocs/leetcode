public class Solution {
    public string GetHappyString(int n, int k) {
        if (k > 3 * (1 << (n - 1))) {
            return "";
        }
        string cs = "abc";
        var ans = new System.Text.StringBuilder();
        for (int i = 0; i < n; i++) {
            int remain = 1 << (n - i - 1);
            foreach (char c in cs) {
                if (ans.Length > 0 && ans[ans.Length - 1] == c) {
                    continue;
                }
                if (k <= remain) {
                    ans.Append(c);
                    break;
                }
                k -= remain;
            }
        }
        return ans.ToString();
    }
}
