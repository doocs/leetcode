class Solution {
public:
    int numDistinct(string s, string t) {
        int n = t.size();
        unsigned long long f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (char& a : s) {
            for (int j = n; j; --j) {
                char b = t[j - 1];
                if (a == b) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n];
    }
};