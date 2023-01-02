class Solution {
public:
    bool areNumbersAscending(string s) {
        int pre = 0;
        istringstream is(s);
        string t;
        while (is >> t) {
            if (isdigit(t[0])) {
                int cur = stoi(t);
                if (pre >= cur) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
};