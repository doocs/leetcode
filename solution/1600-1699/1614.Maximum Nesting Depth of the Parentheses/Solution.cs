public class Solution {
    public int MaxDepth(string s) {
        int n = 0, ans = 0;
        foreach (char c in s)
        {
            if (c == '(')
            {
                ans = Math.Max(ans, ++n);
            }
            else if (c == ')')
            {
                --n;
            }
        }
        return ans;
    }
}