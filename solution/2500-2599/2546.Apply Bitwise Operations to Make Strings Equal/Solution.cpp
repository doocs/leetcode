class Solution {
public:
    bool makeStringsEqual(string s, string target) {
        auto a = count(s.begin(), s.end(), '1') > 0;
        auto b = count(target.begin(), target.end(), '1') > 0;
        return a == b;
    }
};