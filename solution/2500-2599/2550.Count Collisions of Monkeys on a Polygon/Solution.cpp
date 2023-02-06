class Solution {
public:
    int monkeyMove(int n) {
        const int mod = 1e9 + 7;
        return (qmi(2, n, mod) - 2 + mod) % mod;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};