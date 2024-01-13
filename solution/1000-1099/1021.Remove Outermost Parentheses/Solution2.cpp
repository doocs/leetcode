class Solution {
public:
    string removeOuterParentheses(string s) {
        string ans;
        int cnt = 0;
        for (char& c : s) {
            if (c == '(') {
                ++cnt;
            }
            if (cnt > 1) {
                ans.push_back(c);
            }
            if (c == ')') {
                --cnt;
            }
        }
        return ans;
    }
};