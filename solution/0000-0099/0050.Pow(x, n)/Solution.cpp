class Solution {
public:
    double myPow(double x, int n) {
        long long N = n;
        return N >= 0 ? qmi(x, N) : 1.0 / qmi(x, -N);
    }

    double qmi(double a, long long k) {
        double res = 1;
        while (k) {
            if (k & 1) {
                res *= a;
            }
            a *= a;
            k >>= 1;
        }
        return res;
    }
};