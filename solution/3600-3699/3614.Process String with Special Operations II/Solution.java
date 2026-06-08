class Solution {
    public char processStr(String s, long k) {
        long m = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                m = Math.max(0, m - 1);
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
            char c = s.charAt(i);
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
}