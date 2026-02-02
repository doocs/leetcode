class Solution {
public:
    int vowelConsonantScore(string s) {
        int v = 0, c = 0;
        for (char ch : s) {
            if (isalpha(ch)) {
                c++;
                if (string("aeiou").find(ch) != string::npos) {
                    v++;
                }
            }
        }
        c -= v;
        return c == 0 ? 0 : v / c;
    }
};
