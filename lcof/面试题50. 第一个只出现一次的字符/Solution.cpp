class Solution {
public:
    char firstUniqChar(string s) {
        unordered_map<char, bool> um;
        for (char c : s) {
            um[c] = um.find(c) == um.end();
        }
        for (char c : s) {
            if (um[c]) {
                return c;
            }
        }
        return ' ';
    }
};
