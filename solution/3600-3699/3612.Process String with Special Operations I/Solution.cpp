class Solution {
public:
    string processStr(string s) {
        string result;
        for (char c : s) {
            if (isalpha(c)) {
                result += c;
            } else if (c == '*') {
                if (!result.empty()) {
                    result.pop_back();
                }
            } else if (c == '#') {
                result += result;
            } else if (c == '%') {
                ranges::reverse(result);
            }
        }
        return result;
    }
};