class Solution {
public:
    string sortVowels(string s) {
        string vs;
        for (auto c : s) {
            char d = tolower(c);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                vs.push_back(c);
            }
        }
        ranges::sort(vs);
        for (int i = 0, j = 0; i < s.size(); ++i) {
            char d = tolower(s[i]);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                s[i] = vs[j++];
            }
        }
        return s;
    }
};
