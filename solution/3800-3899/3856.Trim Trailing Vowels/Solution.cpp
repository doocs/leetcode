class Solution {
public:
    string trimTrailingVowels(string s) {
        int i = s.size() - 1;
        while (i >= 0 && string("aeiou").find(s[i]) != string::npos) {
            i--;
        }
        return s.substr(0, i + 1);
    }
};
