public class Solution {
    public int Calculate(string s) {
        var stk = new Stack<int>();
        int sign = 1;
        int n = s.Length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == ' ') {
                continue;
            }
            if (s[i] == '+') {
                sign = 1;
            } else if (s[i] == '-') {
                sign = -1;
            } else if (s[i] == '(') {
                stk.Push(ans);
                stk.Push(sign);
                ans = 0;
                sign = 1;
            } else if (s[i] == ')') {
                ans *= stk.Pop();
                ans += stk.Pop();
            } else {
                int num = 0;
                while (i < n && char.IsDigit(s[i])) {
                    num = num * 10 + s[i] - '0';
                    ++i;
                }
                --i;
                ans += sign * num;
            }
        }
        return ans;
    }
}
