class Solution {
public:
    int calculate(string s) {
        int n = s.size();
        int x = 0;
        char sign = '+';
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (isdigit(c)) {
                x = x * 10 + (c - '0');
            }
            if (i == n - 1 || !isdigit(c) && c != ' ') {
                if (sign == '+') {
                    stk.push(x);
                } else if (sign == '-') {
                    stk.push(-x);
                } else if (sign == '*') {
                    int y = stk.top();
                    stk.pop();
                    stk.push(y * x);
                } else if (sign == '/') {
                    int y = stk.top();
                    stk.pop();
                    stk.push(y / x);
                }
                x = 0;
                sign = c;
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