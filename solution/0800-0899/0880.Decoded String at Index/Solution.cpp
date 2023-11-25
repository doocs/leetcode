class Solution {
public:
    string decodeAtIndex(string s, int k) {
        long long m = 0;
        for (char& c : s) {
            if (isdigit(c)) {
                m *= (c - '0');
            } else {
                ++m;
            }
        }
        for (int i = s.size() - 1;; --i) {
            k %= m;
            if (k == 0 && isalpha(s[i])) {
                return string(1, s[i]);
            }
            if (isdigit(s[i])) {
                m /= (s[i] - '0');
            } else {
                --m;
            }
        }
    }
};