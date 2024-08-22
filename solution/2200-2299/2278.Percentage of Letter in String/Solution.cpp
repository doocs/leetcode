class Solution {
public:
    int percentageLetter(string s, char letter) {
        return 100 * ranges::count(s, letter) / s.size();
    }
};
