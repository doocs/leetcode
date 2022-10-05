class Solution {
public:
    string removeOuterParentheses(string s) {
        string ans;
        int cnt = 0;
        for (char& c : s) {
            if (c == '(') {
                if (++cnt > 1) {
                    ans.push_back(c);
                }
            } else {
                if (--cnt) {
                    ans.push_back(c);
                }
            }
        }
        return ans;
    }
};