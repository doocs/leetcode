class Solution {
public:
    string reformat(string s) {
        string a = "", b = "";
        for (char c : s) {
            if (isdigit(c))
                a += c;
            else
                b += c;
        }
        int m = a.size(), n = b.size();
        if (abs(m - n) > 1) return "";
        string ans = "";
        for (int i = 0; i < min(m, n); ++i) {
            if (m > n) {
                ans += a[i];
                ans += b[i];
            } else {
                ans += b[i];
                ans += a[i];
            }
        }
        if (m > n) ans += a[m - 1];
        if (m < n) ans += b[n - 1];
        return ans;
    }
};