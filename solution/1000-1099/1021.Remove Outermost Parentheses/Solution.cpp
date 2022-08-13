class Solution {
public:
    string removeOuterParentheses(string s) {
        string res;
        int depth = 0;
        for (char c : s) {
            if (c == '(') {
                depth++;
            }
            if (depth != 1) {
                res.push_back(c);
            }
            if (c == ')') {
                depth--;
            }
        }
        return res;
    }
};