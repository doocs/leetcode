class Solution {
public:
    bool isNumber(string s) {
        int n = s.size();
        int i = 0;
        if (s[i] == '+' || s[i] == '-') ++i;
        if (i == n) return false;
        if (s[i] == '.' && (i + 1 == n || s[i + 1] == 'e' || s[i + 1] == 'E')) return false;
        int dot = 0, e = 0;
        for (int j = i; j < n; ++j) {
            if (s[j] == '.') {
                if (e || dot) return false;
                ++dot;
            } else if (s[j] == 'e' || s[j] == 'E') {
                if (e || j == i || j == n - 1) return false;
                ++e;
                if (s[j + 1] == '+' || s[j + 1] == '-') {
                    if (++j == n - 1) return false;
                }
            } else if (s[j] < '0' || s[j] > '9')
                return false;
        }
        return true;
    }
};