class Solution {
public:
    int numberOfArrays(string s, int k) {
        constexpr int mod = 1e9 + 7;
        int n = s.size(), d = to_string(k).size();
        vector<int> f(d + 1);
        f[n % (d + 1)] = 1;
        for (int i = n - 1; i >= 0; --i) {
            int ways = 0;
            if (s[i] != '0') {
                long long value = 0;
                for (int j = i; j < n && j < i + d; ++j) {
                    value = value * 10 + s[j] - '0';
                    if (value > k) break;
                    ways = (ways + f[(j + 1) % (d + 1)]) % mod;
                }
            }
            f[i % (d + 1)] = ways;
        }
        return f[0];
    }
};
