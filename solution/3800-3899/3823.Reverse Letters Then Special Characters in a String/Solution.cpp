class Solution {
public:
    string reverseByType(string s) {
        string a, b;

        for (char c : s) {
            if (isalpha(c)) {
                a.push_back(c);
            } else {
                b.push_back(c);
            }
        }

        int j = a.size(), k = b.size();
        for (int i = 0; i < s.size(); ++i) {
            if (isalpha(s[i])) {
                s[i] = a[--j];
            } else {
                s[i] = b[--k];
            }
        }

        return s;
    }
};
