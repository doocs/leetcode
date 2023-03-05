public class Solution {
    public int MaxDepth(string s) {
        int ans = 0, d = 0;
        foreach(char c in s) {
            if (c == '(') {
                ans = Math.Max(ans, ++d);
            } else if (c == ')') {
                --d;
            }
        }
        return ans;
    }
}