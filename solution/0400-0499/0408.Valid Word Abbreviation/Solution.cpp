class Solution {
public:
    bool validWordAbbreviation(string word, string abbr) {
        int i = 0, j = 0;
        int m = word.size(), n = abbr.size();
        while (i < m) {
            if (j >= n) {
                return false;
            }
            if (word[i] == abbr[j]) {
                ++i;
                ++j;
                continue;
            }
            int k = j;
            while (k < n && isdigit(abbr[k])) {
                ++k;
            }
            string t = abbr.substr(j, k - j);
            if (k == j || t[0] == '0') {
                return false;
            }
            int x = stoi(t);
            if (x == 0) {
                return false;
            }
            i += x;
            j = k;
        }
        return i == m && j == n;
    }
};