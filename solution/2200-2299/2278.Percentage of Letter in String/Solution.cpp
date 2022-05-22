class Solution {
public:
    int percentageLetter(string s, char letter) {
        int cnt = 0;
        for (char& c : s) cnt += c == letter;
        return cnt * 100 / s.size();
    }
};