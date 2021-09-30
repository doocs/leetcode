typedef unsigned long long ULL;
class Solution {
public:
    string longestPrefix(string s) {
        int base = 131;
        int n = 100010;
        ULL p[100010];
        p[0] = 1;
        ULL h[100010];
        h[0] = 0;
        for (int i = 1; i <= s.size(); i++)
        {
            p[i] = p[i - 1] * base;
            h[i] = h[i - 1] * base + s[i - 1];
        }
        int l = s.size();
        for (int i = l - 1; i >= 1; i--)
        {
            ULL prefix = h[i];
            ULL suffix = h[l] - h[l - i] * p[i];
            if (prefix == suffix)
            {
                return s.substr(0, i);
            }
        }
        return "";
    }
};