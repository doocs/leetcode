class Solution {
public:
    int findDerangement(int n) {
        long long a = 1, b = 0;
        const int mod = 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            long long c = (i - 1) * (a + b) % mod;
            a = b;
            b = c;
        }
        return b;
    }
};