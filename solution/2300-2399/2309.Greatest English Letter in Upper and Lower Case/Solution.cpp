class Solution {
public:
    string greatestLetter(string s) {
        unordered_set<char> ss;
        for (char& c : s) ss.insert(c);
        for (char c = 'Z'; c >= 'A'; --c)
            if (ss.count(c) && ss.count(char(c + 32)))
                return string(1, c);
        return "";
    }
};