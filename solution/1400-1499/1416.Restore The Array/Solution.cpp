class Solution {
public:
    int numberOfArrays(string s, int k) {
        const int mod = 1e9 + 7;
        int n = s.size();
        vector<int> f(n + 1);
        f[n] = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == '0') {
                continue;
            }
            long long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + s[j] - '0';
                if (x > k) {
                    break;
                }
                f[i] = (f[i] + f[j + 1]) % mod;
            }
        }
        return f[0];
    }
};
