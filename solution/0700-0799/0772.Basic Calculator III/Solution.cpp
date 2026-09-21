class Solution {
public:
    int calculate(string s) {
        queue<char> q;
        for (char c : s) {
            q.push(c);
        }
        return dfs(q);
    }

private:
    int dfs(queue<char>& q) {
        long long num = 0;
        char sign = '+';
        vector<long long> stk;
        while (!q.empty()) {
            char c = q.front();
            q.pop();
            if (isdigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = dfs(q);
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || q.empty()) {
                if (sign == '+') {
                    stk.push_back(num);
                } else if (sign == '-') {
                    stk.push_back(-num);
                } else if (sign == '*') {
                    stk.back() *= num;
                } else {
                    stk.back() /= num;
                }
                num = 0;
                sign = c;
            }
            if (c == ')') {
                break;
            }
        }
        return accumulate(stk.begin(), stk.end(), 0LL);
    }
};
