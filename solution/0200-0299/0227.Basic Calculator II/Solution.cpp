class Solution {
public:
    int calculate(string s) {
        int v = 0, n = s.size();
        char sign = '+';
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (isdigit(c)) v = v * 10 + (c - '0');
            if (i == n - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+')
                    stk.push(v);
                else if (sign == '-')
                    stk.push(-v);
                else if (sign == '*') {
                    int t = stk.top();
                    stk.pop();
                    stk.push(t * v);
                } else {
                    int t = stk.top();
                    stk.pop();
                    stk.push(t / v);
                }
                sign = c;
                v = 0;
            }
        }
        int ans = 0;
        while (!stk.empty()) {
            ans += stk.top();
            stk.pop();
        }
        return ans;
    }
};