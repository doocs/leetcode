class Solution {
public:
    string resultingString(string s) {
        string stk;
        for (char c : s) {
            if (stk.size() && (abs(stk.back() - c) == 1 || abs(stk.back() - c) == 25)) {
                stk.pop_back();
            } else {
                stk.push_back(c);
            }
        }
        return stk;
    }
};