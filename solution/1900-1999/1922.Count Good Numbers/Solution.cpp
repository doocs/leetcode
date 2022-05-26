int MOD = 1000000007;

class Solution {
public:
    int countGoodNumbers(long long n) {
        return (int) (myPow(5, (n + 1) >> 1) * myPow(4, n >> 1) % MOD);
    }

private:
    long long myPow(long long x, long long n) {
        long long res = 1;
        while (n) {
            if ((n & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
};