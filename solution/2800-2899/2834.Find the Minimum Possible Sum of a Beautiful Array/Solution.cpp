class Solution {
public:
    int minimumPossibleSum(int n, int target) {
        const int mod = 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (1LL + n) * n / 2 % mod;
        }
        long long a = (1LL + m) * m / 2 % mod;
        long long b = (1LL * target + target + n - m - 1) * (n - m) / 2 % mod;
        return (a + b) % mod;
    }
};