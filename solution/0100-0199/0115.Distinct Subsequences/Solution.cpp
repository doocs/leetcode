class Solution {
public:
    int numDistinct(string s, string t) {
        int n = t.size();
        vector<unsigned long long> f(n + 1);
        f[0] = 1;
        for (char c : s) {
            for (int j = n - 1; j >= 0; --j) {
                if (c == t[j]) {
                    f[j + 1] += f[j];
                }
            }
        }
        return f[n];
    }
};
