class Solution {
public:
    string reverseWords(string s) {
        for (int i = 0, n = s.size(); i < n; ++i) {
            int j = i;
            while (++j < n && s[j] != ' ')
                ;
            reverse(s.begin() + i, s.begin() + j);
            i = j;
        }
        return s;
    }
};