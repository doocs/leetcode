class Solution {
public:
    int lengthOfLastWord(string s) {
        int i = s.size() - 1;
        while (~i && s[i] == ' ') {
            --i;
        }
        int j = i;
        while (~j && s[j] != ' ') {
            --j;
        }
        return i - j;
    }
};