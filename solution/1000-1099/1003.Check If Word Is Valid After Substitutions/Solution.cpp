class Solution {
public:
    bool isValid(string s) {
        if (s.size() % 3) {
            return false;
        }
        string stk;
        for (char c : s) {
            int n = stk.size();
            if (c == 'c' && n > 1 && stk[n - 2] == 'a' && stk[n - 1] == 'b') {
                stk.pop_back();
                stk.pop_back();
            } else {
                stk.push_back(c);
            }
        }
        return stk.empty();
    }
};