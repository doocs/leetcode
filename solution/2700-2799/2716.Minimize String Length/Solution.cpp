class Solution {
public:
    int minimizedStringLength(string s) {
        unordered_set<char> ss(s.begin(), s.end());
        return ss.size();
    }
};