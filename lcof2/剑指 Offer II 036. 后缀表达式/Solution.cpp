class Solution {
public:
    int evalRPN(vector<string>& tokens) {
        stack<int> stk;
        for (auto& t : tokens) {
            if (t.size() > 1 || isdigit(t[0])) {
                stk.push(stoi(t));
            } else {
                int y = stk.top();
                stk.pop();
                int x = stk.top();
                stk.pop();
                if (t[0] == '+')
                    stk.push(x + y);
                else if (t[0] == '-')
                    stk.push(x - y);
                else if (t[0] == '*')
                    stk.push(x * y);
                else
                    stk.push(x / y);
            }
        }
        return stk.top();
    }
};