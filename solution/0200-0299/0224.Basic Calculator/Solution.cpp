class Solution {
public:
    int calculate(string s) {
        stack<int> stk;
        int ans = 0, sign = 1;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (isdigit(s[i])) {
                int x = 0;
                int j = i;
                while (j < n && isdigit(s[j])) {
                    x = x * 10 + (s[j] - '0');
                    ++j;
                }
                ans += sign * x;
                i = j - 1;
            } else if (s[i] == '+') {
                sign = 1;
            } else if (s[i] == '-') {
                sign = -1;
            } else if (s[i] == '(') {
                stk.push(ans);
                stk.push(sign);
                ans = 0;
                sign = 1;
            } else if (s[i] == ')') {
                ans *= stk.top();
                stk.pop();
                ans += stk.top();
                stk.pop();
            }
        }
        return ans;
    }
};