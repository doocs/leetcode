class Solution {
public:
    int minimizedStringLength(string s) {
        return unordered_set<char>(s.begin(), s.end()).size();
    }
};
