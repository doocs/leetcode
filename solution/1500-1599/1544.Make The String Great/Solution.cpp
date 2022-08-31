class Solution {
public:
    string makeGood(string s) {
        string stk;
        for (char c : s) {
            if (stk.empty() || abs(stk.back() - c) != 32) {
                stk += c;
            } else {
                stk.pop_back();
            }
        }
        return stk;
    }
};