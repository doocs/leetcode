class Solution {
public:
    string reverseWords(string s) {
        string res;
        int i = s.size() - 1;
        while (i >= 0) {
            if (s[i] == ' ') {
                i--;
            } else {
                int j = i;
                while (i >= 0 && s[i] != ' ') {
                    i--;
                }
                res += s.substr(i + 1, j - i);
                res.push_back(' ');
            }
        }
        return res.substr(0, res.size() - 1);
    }
};