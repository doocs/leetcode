class Solution {
public:
    string rearrangeString(string s, char x, char y) {
        int i = 0;
        for (int j = 0; j < s.size(); j++) {
            if (s[j] == y) {
                swap(s[i], s[j]);
                i++;
            }
        }
        return s;
    }
};