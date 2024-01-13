class Solution {
public:
    char findTheDifference(string s, string t) {
        int a = 0, b = 0;
        for (char& c : s) {
            a += c;
        }
        for (char& c : t) {
            b += c;
        }
        return b - a;
    }
};