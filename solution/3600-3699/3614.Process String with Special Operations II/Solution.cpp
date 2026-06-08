class Solution {
public:
    char processStr(string s, long long k) {
        long long m = 0;
        for (char c : s) {
            if (c == '*') {
                m = max(0LL, m - 1);
            } else if (c == '#') {
                m <<= 1;
            } else if (c != '%') {
                m += 1;
            }
        }
        if (k >= m) {
            return '.';
        }
        for (int i = s.length() - 1;; i--) {
            char c = s[i];
            if (c == '*') {
                m += 1;
            } else if (c == '#') {
                m /= 2;
                if (k >= m) {
                    k -= m;
                }
            } else if (c == '%') {
                k = m - 1 - k;
            } else {
                m -= 1;
                if (k == m) {
                    return c;
                }
            }
        }
    }
};