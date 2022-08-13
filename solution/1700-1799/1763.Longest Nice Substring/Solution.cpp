class Solution {
public:
    string longestNiceSubstring(string s) {
        int n = s.size();
        string ans = "";
        for (int i = 0; i < n; ++i) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; ++j) {
                if (islower(s[j]))
                    lower |= 1 << (s[j] - 'a');
                else
                    upper |= 1 << (s[j] - 'A');
                if (lower == upper && j - i + 1 > ans.size()) ans = s.substr(i, j - i + 1);
            }
        }
        return ans;
    }
};