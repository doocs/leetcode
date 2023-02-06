class Solution {
public:
    bool parseBoolExpr(string expression) {
        stack<char> stk;
        for (char c : expression) {
            if (c != '(' && c != ')' && c != ',')
                stk.push(c);
            else if (c == ')') {
                int t = 0, f = 0;
                while (stk.top() == 't' || stk.top() == 'f') {
                    t += stk.top() == 't';
                    f += stk.top() == 'f';
                    stk.pop();
                }
                char op = stk.top();
                stk.pop();
                if (op == '!') c = f ? 't' : 'f';
                if (op == '&') c = f ? 'f' : 't';
                if (op == '|') c = t ? 't' : 'f';
                stk.push(c);
            }
        }
        return stk.top() == 't';
    }
};