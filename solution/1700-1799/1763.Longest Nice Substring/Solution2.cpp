class Solution {
public:
    string longestNiceSubstring(string s) {
        int n = s.size();
        int k = -1, mx = 0;
        for (int i = 0; i < n; ++i) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; ++j) {
                char c = s[j];
                if (islower(c))
                    lower |= 1 << (c - 'a');
                else
                    upper |= 1 << (c - 'A');
                if (lower == upper && mx < j - i + 1) {
                    mx = j - i + 1;
                    k = i;
                }
            }
        }
        return k == -1 ? "" : s.substr(k, mx);
    }
};