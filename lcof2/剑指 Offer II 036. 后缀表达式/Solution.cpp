class Solution {
public:
    int evalRPN(vector<string>& tokens) {
        stack<int> stk;
        for (const auto& token : tokens) {
            if (token.size() > 1 || isdigit(token[0])) {
                stk.push(stoi(token));
            } else {
                int y = stk.top();
                stk.pop();
                int x = stk.top();
                stk.pop();
                if (token[0] == '+') stk.push(x + y);
                else if (token[0] == '-') stk.push(x - y);
                else if (token[0] == '*') stk.push(x * y);
                else stk.push(x / y);
            }
        }
        return stk.top();
    }
};
