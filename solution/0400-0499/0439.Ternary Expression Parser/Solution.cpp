class Solution {
public:
    string parseTernary(string expression) {
        string stk;
        bool cond = false;
        reverse(expression.begin(), expression.end());
        for (char& c : expression) {
            if (c == ':') {
                continue;
            }
            if (c == '?') {
                cond = true;
            } else {
                if (cond) {
                    if (c == 'T') {
                        char x = stk.back();
                        stk.pop_back();
                        stk.pop_back();
                        stk.push_back(x);
                    } else {
                        stk.pop_back();
                    }
                    cond = false;
                } else {
                    stk.push_back(c);
                }
            }
        }
        return {stk[0]};
    }
};