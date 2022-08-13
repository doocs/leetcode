class Solution {
public:
    int minAddToMakeValid(string s) {
        stack<char> stk;
        for (char& c : s) {
            if (c == '(')
                stk.push(c);
            else {
                if (!stk.empty() && stk.top() == '(') {
                    stk.pop();
                } else
                    stk.push(c);
            }
        }
        return stk.size();
    }
};