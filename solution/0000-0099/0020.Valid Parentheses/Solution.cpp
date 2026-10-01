class Solution {
public:
    bool isValid(string s) {
        string stk;
        unordered_map<char, char> d{{'(', ')'}, {'[', ']'}, {'{', '}'}};
        for (char c : s) {
            if (d.contains(c)) {
                stk.push_back(d[c]);
            } else if (stk.empty() || stk.back() != c) {
                return false;
            } else {
                stk.pop_back();
            }
        }
        return stk.empty();
    }
};
