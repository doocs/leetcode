class Solution {
public:
    string makeAntiPalindrome(string s) {
        sort(s.begin(), s.end());
        int n = s.length();
        int m = n / 2;
        if (s[m] == s[m - 1]) {
            int i = m;
            while (i < n && s[i] == s[i - 1]) {
                ++i;
            }
            for (int j = m; j < n && s[j] == s[n - j - 1]; ++i, ++j) {
                if (i >= n) {
                    return "-1";
                }
                swap(s[i], s[j]);
            }
        }
        return s;
    }
};